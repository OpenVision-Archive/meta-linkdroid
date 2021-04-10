DESCRIPTION = "Create NIM sockets"
MAINTAINER = "mnigma"
SECTION = "base"
LICENSE = "proprietary"

COMPATIBLE_MACHINE = "^(alien5)$"

require conf/license/license-gplv2.inc

inherit update-rc.d

#INHIBIT_PACKAGE_STRIP = "1"

SRC_URI = "file://lkdosth \
           file://lkdosth.init \
"

S = "${WORKDIR}"

do_install () {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/lkdosth ${D}${bindir}/
    install -d ${D}${INIT_D_DIR}/
    install -m 0755 ${WORKDIR}/lkdosth.init ${D}${INIT_D_DIR}/${PN}
}

FILES_${PN} = "${bindir} ${sysconfdir}"

INITSCRIPT_NAME = "${PN}"
INITSCRIPT_PARAMS = "defaults 08"

do_rm_work() {
}
