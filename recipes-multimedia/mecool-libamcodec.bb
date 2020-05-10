SUMMARY = "Amlogic codecs library"

require mecool-lib.inc

S = "${WORKDIR}/libamcodec-openvision/amcodec"

SRC_URI_append = " file://libamcodec.pc"

DEPENDS = "mecool-libamadec"
RDEPENDS_${PN} = "mecool-libamadec"

EXTRA_OEMAKE = " \
    'CC=${CC}' \
    'CFLAGS=-O2 -fPIC -I${S}/include -I${S} -I${S}/codec -I${STAGING_INCDIR}/amlogic/amadec' \
    'LDFLAGS=-lamadec -lm -lc -shared -Wl,--shared -Wl,-soname,libamcodec.so' \
"

do_install() {
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/libamcodec.pc ${D}${libdir}/pkgconfig/

    install -d ${D}${includedir}/amcodec
    install -m 0755 ${S}/include/*.h ${D}${includedir}/amcodec

    install -d ${D}${includedir}/amcodec/amports
    install -m 0755 ${S}/include/amports/*.h ${D}${includedir}/amcodec/amports

    install -d ${D}${includedir}/amcodec/ppmgr
    install -m 0755 ${S}/include/ppmgr/*.h ${D}${includedir}/amcodec/ppmgr

    install -d ${D}${includedir}/amcodec/yuvplayer
    install -m 0755 ${S}/include/yuvplayer/*.h ${D}${includedir}/amcodec/yuvplayer

    install -d ${D}${includedir}/amlogic/amcodec
    install -m 0755 ${S}/include/*.h ${D}${includedir}/amlogic/amcodec

    install -d ${D}${includedir}/amlogic/amcodec/amports
    install -m 0755 ${S}/include/amports/*.h ${D}${includedir}/amlogic/amcodec/amports

    install -d ${D}${includedir}//amlogic/amcodec/ppmgr
    install -m 0755 ${S}/include/ppmgr/*.h ${D}${includedir}/amlogic/amcodec/ppmgr

    install -d ${D}${includedir}//amlogic/amcodec/yuvplayer
    install -m 0755 ${S}/include/yuvplayer/*.h ${D}${includedir}/amlogic/amcodec/yuvplayer

    install -d ${D}${libdir}
    install -m 0755 ${S}/*.so ${D}${libdir}
}
