SUMMARY = "Enigma2 A5 extra LKMs for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"
require conf/license/license-close.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "^(alien5)$"

DEPENDS = "virtual/${TARGET_PREFIX}gcc"

SRCDATE = "20180314"

PV = "${KV}+${SRCDATE}"

SRC_URI = "file://alien5-extra-${SRCDATE}.zip"

S = "${WORKDIR}"

do_compile() {
}

do_install() {
	install -d ${D}${nonarch_base_libdir}/modules/${KV}/kernel/drivers/a5/
	install -m 0755 ${WORKDIR}/ampanel.ko ${D}${nonarch_base_libdir}/modules/${KV}/kernel/drivers/a5/
	install -m 0755 ${WORKDIR}/se2io_se.ko ${D}${nonarch_base_libdir}/modules/${KV}/kernel/drivers/a5/
}

do_package_qa() {
}

FILES_${PN} += "${nonarch_base_libdir}/modules/${KV}/extra"
