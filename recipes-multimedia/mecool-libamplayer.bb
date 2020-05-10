SUMMARY = "Amlogic player library"

require mecool-lib.inc

S = "${WORKDIR}/libamcodec-openvision/amplayer"

SRC_URI_append = " file://libamplayer.pc"

DEPENDS = "mecool-libamadec mecool-libamcodec"
RDEPENDS_${PN} = "mecool-libamadec mecool-libamcodec"

EXTRA_OEMAKE = "\
    'CC=${CC}' \
    'LD=${LD}' \
    'CFLAGS=-fPIC -DENABLE_FREE_SCALE -I${S}/include -I${S}/../amffmpeg -I${S}/player \
    -I${S}/player/include -I${S} -I${STAGING_INCDIR}/amlogic/amcodec' \
    'LDFLAGS=-lamadec -shared' \
"

do_install() {
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/libamplayer.pc ${D}${libdir}/pkgconfig/

    install -d ${D}${includedir}/player
    cp -pR  ${S}/player/include/*.h ${D}${includedir}/player

    install -d ${D}${includedir}/amlogic/player
    cp -pR  ${S}/player/include/*.h ${D}${includedir}/amlogic/player

    install -d ${D}${libdir}
    install -m 0755 ${S}/*.so ${D}${libdir}
}
