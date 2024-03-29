KERNELVERSION = "3.14.29"

BOX_BRAND = "mecool"

MTD_BLACK  = "mmcblk0"
KERNEL_FILE = "kernel.itb"
ROOTFS_FILE = "rootfs.tar.bz2"

PREFERRED_PROVIDER_virtual/kernel = "linux-amlogic"
PREFERRED_VERSION_linux-amlogic = "${KERNELVERSION}"

MACHINEKERNEL = "amlogic"

# Amlogic defaults
require conf/machine/include/arm/arch-armv8.inc
require conf/machine/include/soc-family.inc

IMAGE_CLASSES += "sdcard_image-amlogic"
SDCARD_ROOTFS ?= "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.ext4"
IMAGE_FSTYPES ?= "amlogicsdimg"
IMAGE_ROOTFS_SIZE = "476562"
IMAGE_EXTRA_SPACE = "0"
IMAGE_OVERHEAD_FACTOR = "1.0"

# Serial console to be used
SERIAL_CONSOLE = "115200 ttyS0"

KERNEL_CLASSES += "aarch64-fitimage"
KERNEL_IMAGETYPE = "fitImage"

UBOOT_ENTRYPOINT = "0x01080000"
UBOOT_LOADADDRESS = "0x01080000"

INITRAMFS_IMAGE = "amlogic-initramfs"

MACHINE_FEATURES += "bluetooth usbhost vfat alsa dvb-c nolcd hdmicec fb emmc nogamma HDMI AV_JACK kodi17"


MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "\
	amlogic-autoscript \
	amlogic-libadpcm \
	amlogic-libamadec \
	amlogic-libamavutils \
	amlogic-libamcodec \
	amlogic-libamplayer \
	amlogic-libamr \
	amlogic-libape \
	amlogic-libcook \
	amlogic-libfaad \
	amlogic-libflac \
	amlogic-liblpcm \
	amlogic-libmad \
	amlogic-libpcm \
	amlogic-libraac \
	amlogic-mali \
	amlogic-shutdown \
	amlogic-u-boot-fw-utils \
	amremote \
	firmware-rpi \
	kernel-module-e2-procfs \
	kernel-modules \
	libmad \
	mecool-amlsetfb \
	mecool-gst-amlavsink1 \
	mecool-dvb-modules-e2procfs \
	mecool-setup \
	"

KERNEL_MODULE_AUTOLOAD += "\
	mali \
	dhd \
	aml \
	aml_fe \
	avl6862 \
	r848a \
	sysfillrect \
	syscopyarea \
	ch341 \
	"

PREFERRED_PROVIDER_jpeg = "libjpeg-turbo"
PREFERRED_PROVIDER_jpeg-native = "libjpeg-turbo-native"

DVBMEDIASINK_CONFIG = "--with-wmv --with-pcm --with-eac3 --with-h265 --with-dtsdownmix"

CC_append_pn-enigma2 = " -I${STAGING_INCDIR}/amlogic/amcodec -lamcodec"
EXTRA_OECONF_append_pn-enigma2 = " --with-alsa --with-alsacard=hw:0 --with-alsamixer=Master --with-fbdev=/dev/fb0 --with-lcddev=/dev/null --with-amlogic"
DEPENDS_append_pn-enigma2 = " libdvbcsa amlogic-libamcodec amlogic-libamadec gstreamer1.0 gstreamer1.0-plugins-base"
EXTRA_OECONF_append_pn-tuxtxt-enigma2 = " --with-fbdev=/dev/fb0"
DEPENDS_append_pn-enigma2-plugin-systemplugins-servicemp3 = " amlogic-libamcodec "
CC_append_pn-showiframe = " -DHAVE_AMLOGIC -I${STAGING_INCDIR}/amlogic/amcodec -lamcodec -lgstpbutils-1.0 -lgstreamer-1.0 -lgobject-2.0 -lglib-2.0"
DEPENDS_append_pn-showiframe = " amlogic-libamcodec gstreamer1.0 gstreamer1.0-libav gstreamer1.0-plugins-base gstreamer1.0-plugins-good gstreamer1.0-plugins-ugly"

DISTRO_FEATURES_remove += "x11 wayland"

IMAGE_CMD_amlogicsdimg_prepend += " \
	cp -f ${DEPLOY_DIR_IMAGE}/${KERNEL_DEVICETREE} ${DEPLOY_DIR_IMAGE}/dtb.img; \
	"

IMAGE_CMD_amlogicsdimg_append = " \
	mkdir -p ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}; \
	mv -f ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.img ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/${IMAGE_NAME}.img; \
	echo ${IMAGE_NAME} > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/imageversion; \
	echo "https://forum.openvision.tech/app.php/donate" > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/donate.txt; \
	rm -f ${DEPLOY_DIR_IMAGE}/*.zip; \
	cd ${DEPLOY_DIR_IMAGE}; \
	zip ${IMAGE_NAME}_sdcard.zip ${IMAGEDIR}/*; \
	rm -f ${DEPLOY_DIR_IMAGE}/*.ext4; \
	rm -f ${DEPLOY_DIR_IMAGE}/*.img; \
	rm -Rf ${IMAGEDIR}; \
	"

IMAGE_CMD_prepend = "\
	rm -Rf ${IMAGE_ROOTFS}/tmp/*; \
	"

# We know about rtc
MACHINE_FEATURES_BACKFILL_CONSIDERED += "rtc"

RCNAME ?= "k1pro"

PREFERRED_PROVIDER_virtual/libgles1 ?= "am-opengl-amlogic"
PREFERRED_PROVIDER_virtual/libgles2 ?= "am-opengl-amlogic"
PREFERRED_PROVIDER_virtual/egl ?= "am-opengl-amlogic"

REFERRED_PROVIDER_virtual/kodi = "stb-kodi-mecool"
