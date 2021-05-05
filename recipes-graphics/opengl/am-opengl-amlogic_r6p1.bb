SUMMARY = "libgles for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

PACKAGE_ARCH = "${MACHINE_ARCH}"

PROVIDES = "virtual/libgles1 virtual/libgles2 virtual/egl"

COMPATIBLE_MACHINE = "^(k1pro|k2pro|k2prov2|k3pro|k1plus|k1plusv2|alien5)$"

SRC_URI = "http://sources.libreelec.tv/devel/opengl-905-r6p1.tgz \
	file://10-meson_mali.rules \
	" 

SRC_URI[md5sum] = "72d9ae6b59af1312adc92806b22d0a5b"

S = "${WORKDIR}/usr"

INHIBIT_PACKAGE_STRIP = "1"

do_compile() {
}

do_install() {
	install -d ${D}${sysconfdir}/udev/rules.d
	install -d ${D}${includedir}
	install -d ${D}${libdir}
	install -m 0644 ${WORKDIR}/10-meson_mali.rules  ${D}${sysconfdir}/udev/rules.d/
	cp -axr ${S}/include/* ${D}${includedir}/
	cp -ax ${S}/lib/* ${D}${libdir}/
}

do_package_qa() {
}

FILES_${PN} = "${includedir}/* ${libdir}/*  ${sysconfdir}/*"
FILES_${PN}-dev = "${incdir}/*"
