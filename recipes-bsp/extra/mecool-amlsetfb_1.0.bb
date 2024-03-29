SUMMARY = "SysV init scripts for Amlogic framebuffer set-up"
DESCRIPTION = "Provides basic set-up for the amlogic framebuffer"
SECTION = "base"
include conf/license/license-gplv2.inc

COMPATIBLE_MACHINE = "^(k1pro|k2pro|k2prov2|k3pro|k1plus|k1plusv2)$"

inherit pkgconfig update-rc.d

INITSCRIPT_NAME = "amlsetfb"
INITSCRIPT_PARAMS = "start 03 S ."

INHIBIT_DEFAULT_DEPS = "1"
RDEPENDS_${PN} = "initscripts fbset fbset-modes"

S = "${WORKDIR}"

SRC_URI = " \
    file://amlsetfb \
    file://aaa \
"

do_install() {
    install -d ${D}${INIT_D_DIR}
    install -m 0755 ${WORKDIR}/amlsetfb  ${D}${INIT_D_DIR}/amlsetfb
    install -m 0755 ${WORKDIR}/aaa  ${D}${INIT_D_DIR}/aaa
}
