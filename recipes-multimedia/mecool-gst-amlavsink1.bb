SUMMARY = "GStreamer AML AVsink plugin"
#  Amlogic GStreamer plugins to send audio es to aml dsp and video es to aml hw decoder. 
#  decode and render will be complete at kernel level.
SECTION = "multimedia"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "^(k1pro|k2pro|k2prov2|k3pro|k1plus|k1plusv2)$"

inherit lib_package pkgconfig autotools

### TODO:
## cram plugins as did not have time to check which ones provide gst/video headers 
## QA still complains about host headers used, does not make any sense
DEPENDS = " gstreamer1.0 mecool-libamcodec mecool-libamavutils mecool-libamplayer gstreamer1.0-libav gstreamer1.0-plugins-base gstreamer1.0-plugins-good gstreamer1.0-plugins-ugly"

SRC_URI = "https://raw.githubusercontent.com/OpenVisionE2/amlogic-libs/master/gst-aml-plugins-1.0.tar.xz"

SRC_URI[md5sum] = "c2f234cac9f0c91ec0b475edbee23c9b"
SRC_URI[sha256sum] = "1b1c6ffc7f5712393a115bfe447b224b3404628c8cd5e06647de04ab5ce573ea"

S = "${WORKDIR}/gst-aml-plugins-1.0"

CFLAGS += "-fPIC -I${S}/common/include -I${S}/common/include/amcodec/amports -I${S}/common/include/amcodec/ppmgr \
    -I${STAGING_INCDIR} -I${S}/common/amstreaminfo -I${S}/common/amlsysctl "
LDFLAGS += "-L${STAGING_LIBDIR} -L${STAGING_BASELIBDIR} "

FILES_${PN} += "${libdir}/* ${libdir}/libcommon.a"
FILES_${PN}-dbg += "${libdir}/gstreamer-1.0/.debug $"
FILES_${PN}-dev += "${S}/include/* "

do_package_qa() {
}

export KCFLAGS = "-Wno-error=implicit-function-declaration"
