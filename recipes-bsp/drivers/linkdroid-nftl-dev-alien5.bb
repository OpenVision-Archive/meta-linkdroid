SUMMARY = "AMLogic 905 nftl_dev driver from LE"
SECTION = "base"
PRIORITY = "required"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "^(alien5)$"

DEPENDS = "virtual/${TARGET_PREFIX}gcc"

require conf/license/license-gplv2.inc

SRCDATE = "20180513"

PV = "${KV}+${SRCDATE}"

SRC_URI = "file://alien5-nftl_dev-${SRCDATE}.zip"

S = "${WORKDIR}"

INHIBIT_PACKAGE_STRIP = "1"

do_compile() {
}

do_install () {
    install -d ${D}${nonarch_base_libdir}/modules/${KV}/kernel/drivers/extra
    install -m 0644 ${S}/aml_nftl_dev.ko ${D}${nonarch_base_libdir}/modules/${KV}/kernel/drivers/extra/
    install -d ${D}/${sysconfdir}/modules-load.d
    echo "aml_nftl_dev" > ${D}/${sysconfdir}/modules-load.d/amlnftldev.conf
}

FILES_${PN} += "${sysconfdir}/modules-load.d/amlnftldev.conf"
FILES_${PN} += "${base_libdir}/modules/${KV}/extra"

do_package_qa() {
}
