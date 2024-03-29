DESCRIPTION = "Create NIM sockets"
SECTION = "base"
LICENSE = "proprietary"
PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "^(alien5|k1pro|k2pro|k2prov2|k3pro|k1plus|k1plusv2)$"

require conf/license/license-gplv2.inc

inherit update-rc.d

SRC_URI = "file://cnim.c \
           file://Makefile \
           file://cnim.init \
"

S = "${WORKDIR}"

do_install () {
    install -d ${D}${bindir}
    install -m 0755 cnim ${D}${bindir}/
    install -d ${D}${INIT_D_DIR}/
    install -m 0755 ${WORKDIR}/cnim.init ${D}${INIT_D_DIR}/${PN}
}

FILES_${PN} = "${bindir} ${sysconfdir}"

INITSCRIPT_NAME = "${PN}"
INITSCRIPT_PARAMS = "defaults 88"

do_rm_work() {
}
