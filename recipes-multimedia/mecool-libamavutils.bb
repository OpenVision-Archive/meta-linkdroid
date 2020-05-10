SUMMARY = "Amlogic audio video utils library"

require mecool-lib.inc

S = "${WORKDIR}/libamcodec-openvision/amavutils"

EXTRA_OEMAKE = "\
    'CC=${CC}' \
    'CFLAGS=-O2 -fPIC -DALSA_OUT -DENABLE_WAIT_FORMAT -I${S}/../amcodec/include -I${S}/include -I${S}' \
    'LDFLAGS=-shared -lm -lrt' \
"

do_install() {
    install -d ${D}${includedir}/amavutils
    install -m 0755 ${S}/include/*.h ${D}${includedir}/amavutils

    install -d ${D}${includedir}/amavutils/cutils
    install -m 0755 ${S}/include/cutils/*.h ${D}${includedir}/amavutils/cutils

    install -d ${D}${includedir}/amlogic/amavutils
    install -m 0755 ${S}/include/*.h ${D}${includedir}/amlogic/amavutils

    install -d ${D}${includedir}/amlogic/amavutils/cutils
    install -m 0755 ${S}/include/cutils/*.h ${D}${includedir}/amlogic/amavutils/cutils

    install -d ${D}${libdir}
    install -m 0755 ${S}/*.so ${D}${libdir}
}
