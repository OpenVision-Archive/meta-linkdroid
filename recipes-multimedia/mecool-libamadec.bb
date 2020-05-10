SUMMARY = "Amlogic audio decoders library"

require mecool-lib.inc

S = "${WORKDIR}/libamcodec-openvision/amadec"

SRC_URI_append = " file://libamadec.pc"

DEPENDS = "mecool-libamavutils alsa-lib rtmpdump"
RDEPENDS_${PN} = "ffmpeg"

EXTRA_OEMAKE = "\
    'CC=${CC}' \
    'CFLAGS=-fPIC -DALSA_OUT -DENABLE_WAIT_FORMAT -I${S}/include -I${S}' \
    'LDFLAGS=-shared -lamavutils -lasound' \
"

do_install() {
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/libamadec.pc ${D}${libdir}/pkgconfig/

    install -d ${D}${includedir}/amadec
    install -m 0755 ${S}/include/*.h ${D}${includedir}/amadec

    install -d ${D}${includedir}/amadec/cutils
    install -m 0755 ${S}/include/cutils/*.h ${D}${includedir}/amadec/cutils

    install -d ${D}${includedir}/amlogic/amadec
    install -m 0755 ${S}/include/*.h ${D}${includedir}/amlogic/amadec

    install -d ${D}${includedir}/amlogic/amadec/cutils
    install -m 0755 ${S}/include/cutils/*.h ${D}${includedir}/amlogic/amadec/cutils

    install -d ${D}${nonarch_base_libdir}/firmware
    install -m 0755 ${S}/firmware/*.bin ${D}${nonarch_base_libdir}/firmware

    install -d ${D}${libdir}
    install -m 0755 ${S}/*.so ${D}${libdir}
}

FILES_${PN} += " ${nonarch_base_libdir}/firmware"
