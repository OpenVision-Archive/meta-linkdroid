LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit lib_package pkgconfig

SRC_URI = "https://raw.githubusercontent.com/OpenVisionE2/amlogic-libs/master/libamcodec-openvision.tar.xz"

SRC_URI[md5sum] = "e23c1c7fa9af0d803edc2af8a4ec59a3"
SRC_URI[sha256sum] = "e4f6121c0eff746f7951ac1d19fc8d4fd69a56ad89bb845e08cc163f8f0a4f99"

COMPATIBLE_MACHINE = "^(k1pro|k2pro|k2prov2|k3pro|k1plus|k1plusv2)$"

FILES_${PN} = "${libdir}/*.so"
FILES_${PN}-dev = "${includedir}/*"

do_package_qa() {
}
