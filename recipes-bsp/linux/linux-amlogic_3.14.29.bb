DESCRIPTION = "Linux kernel for ${MACHINE}"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI[md5sum] = "0f60ae00e4e0787031d61526bf7197f6"
SRC_URI[sha256sum] = "62e309a3755002e963b8089a172b5e39bddbe3356f33ce39dd12fdb0e3a80055"

inherit kernel machine_kernel_pr

DEPENDS = "xz-native bc-native u-boot-mkimage-native virtual/${TARGET_PREFIX}gcc"

# Avoid issues with Amlogic kernel binary components
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_STRIP = "1"
LINUX_VERSION ?= "3.14.29"
LINUX_VERSION_EXTENSION ?= "amlogic"

COMPATIBLE_MACHINE = "^(k1pro|k2pro|k2prov2|k3pro|k1plus|k1plusv2)$"

S = "${WORKDIR}/linux-amlogic-coreelec-amlogic-3.14-nougat"
B = "${WORKDIR}/build"

DEFCONFIG = "mecool"

DTS = "${@ d.getVar('KERNEL_DEVICETREE').replace('.dtb','.dts') }"

SRC_URI = "https://github.com/OpenVisionE2/linux-amlogic-coreelec/archive/amlogic-3.14-nougat.tar.gz \
  file://${OPENVISION_BASE}/openvision-oe/recipes-linux/kernel-patches/kernel-add-support-for-gcc6.patch \
"

do_configure_prepend(){
    sed -i "s/@DISTRONAME@/${MACHINE}/" "${S}/arch/arm64/configs/mecool_defconfig"
}

do_compile_append() {
    if test -n "${KERNEL_DEVICETREE}"; then
    	for DTB in ${KERNEL_DEVICETREE}; do
    		if echo ${DTB} | grep -q '/dts/'; then
    			bbwarn "${DTB} contains the full path to the the dts file, but only the dtb name should be used."
    			DTB=`basename ${DTB} | sed 's,\.dts$,.dtb,g'`
    		fi
    		oe_runmake ${DTB}
    	done
    	# Create directory, this is needed for out of tree builds
    	mkdir -p ${B}/arch/arm64/boot/dts/amlogic/
    	cp ${B}/arch/arm64/boot/dts/amlogic/${KERNEL_DEVICETREE} ${B}/arch/arm64/boot/
    fi
}

do_install_append() {
    install -m 644 ${B}/arch/arm64/boot/${KERNEL_DEVICETREE} ${DEPLOY_DIR_IMAGE}/
}

do_rm_work() {
}

do_package_qa() {
}

# extra tasks
addtask kernel_link_images after do_compile before do_install
