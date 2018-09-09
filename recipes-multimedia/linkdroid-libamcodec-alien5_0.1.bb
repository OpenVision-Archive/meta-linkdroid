SUMMARY = "Amlogic codecs library"
PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "alien5|k1pro|k2pro|k2prov2|k3pro|k1plus"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRCDATE = "20180905"
PR = "${SRCDATE}"

DEPENDS = "linkdroid-libamadec-alien5"
RDEPENDS_${PN} = "linkdroid-libamadec-alien5"

inherit lib_package pkgconfig

SRC_URI[md5sum] = "384aaad50fc89516a2563294413af013"
SRC_URI[sha256sum] = "0b8f6a3b9b011661e85411fc8baa3360d625c49673db217760f52e85e3d43736"

SRC_URI = "http://source.mynonpublic.com/linkdroid/${BPN}-${SRCDATE}.zip"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${libdir}/pkgconfig
    install -d ${D}${sysconfdir}/
    install -m 0644 ${WORKDIR}/libamcodec.pc ${D}${libdir}/pkgconfig/
    install -d ${D}${includedir}/amlogic/amcodec
    install -d ${D}${includedir}/amcodec
    cp -pR ${S}/include/* ${D}${includedir}/amlogic/amcodec
    cp -pR ${S}/include/* ${D}${includedir}/amcodec
    install -d ${D}${libdir}
    install -m 0755  ${WORKDIR}/libamcodec.so.0.0  ${D}${libdir}/
    cd ${D}${libdir}
    ln -sf libamcodec.so.0.0 libamcodec.so
}

do_configure() {
}

do_compile() {
}

do_package_qa() {
}

INSANE_SKIP_${PN} = "already-stripped dev-so ldflags dev-deps"
