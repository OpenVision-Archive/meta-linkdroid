BOX_BRAND = "linkdroid"

require conf/distro/multilib.inc

MTD_BLACK  = "mmcblk0"
KERNEL_FILE = "kernel.itb"
ROOTFS_FILE = "rootfs.tar.bz2"

PREFERRED_PROVIDER_virtual/kernel = "linux-linkdroid"
PREFERRED_VERSION_linux-linkdroid = "3.14.29"

MACHINEKERNEL = "linkdroid"

# Amlogic defaults
require conf/machine/include/arm/arch-armv8.inc
require conf/machine/include/soc-family.inc

IMAGE_CLASSES += "sdcard_image-amlogic"
SDCARD_ROOTFS ?= "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.ext4"
IMAGE_FSTYPES ?= "amlogicsdimg"
IMAGE_ROOTFS_SIZE = "476562"
IMAGE_EXTRA_SPACE = "0"
IMAGE_OVERHEAD_FACTOR = "1.0"

BB_HASH_IGNORE_MISMATCH = "1"

# Serial console to be used
SERIAL_CONSOLE = "115200 ttyS0"

KERNEL_CLASSES += "aarch64-fitimage"
KERNEL_IMAGETYPE = "fitImage"

UBOOT_ENTRYPOINT = "0x01080000"
UBOOT_LOADADDRESS = "0x01080000"

INITRAMFS_IMAGE = "amlogic-initramfs"

MACHINE_FEATURES = "bluetooth usbhost vfat alsa dvb-c nolcd hdmicec fb emmc nogamma multilib"

DVBPROVIDER = "kernel"
DVBSKYPROVIDER = "kernel"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "\
    amlogic-autoscript \
    amlogic-gst-amlavsink1 \
    amlogic-mali \
    amlogic-shutdown \
    amremote \
    kernel-modules \
    libmad \
    linkdroid-amlsetfb \
    linkdroid-libamadec-${MACHINE} \
    linkdroid-libamavutils-${MACHINE} \
    linkdroid-libamcodec-${MACHINE} \
    linkdroid-libamplayer-${MACHINE} \
    linkdroid-libavlib-audio-so-${MACHINE} \
    linkdroid-dvb-modules-${MACHINE} \
    linkdroid-extra-${MACHINE} \
    "
	
KERNEL_MODULE_AUTOLOAD += "\
    se2io_se \
    ampanel \
    a5stb \
    mali \
    rfcomm \
    hidp \
    bluetooth \
    6lowpan-iphc \
    libcrc32c \
    mceusb \
    rc-core \
    cfg80211 \
    8189es \
"

### TODO:     8189es builitin with cfg80211
MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "\
    kernel-module-cdfs \
    kernel-module-sysfillrect \
    kernel-module-syscopyarea \
    kernel-module-mceusb \
    kernel-module-rc-core \
    kernel-module-CH341 \
    kernel-module-rfcomm \
    kernel-module-hidp \
    kernel-module-bluetooth \
    kernel-module-6lowpan-iphc \
    kernel-module-libcrc32c \
    kernel-module-xfs \
    kernel-module-8189es \
    kernel-module-cfg80211 \
    "

MACHINE_EXTRA_RRECOMMENDS += "\
    linkdroid-lkdosth \
    amlogic-cnim \
    "

PREFERRED_PROVIDER_jpeg = "libjpeg-turbo"
PREFERRED_PROVIDER_jpeg-native = "libjpeg-turbo-native"

DVBMEDIASINK_CONFIG = "--with-wmv --with-pcm --with-eac3 --with-h265 --with-dtsdownmix"

EXTRA_OECONF_append_pn-enigma2 = " --with-alsa --with-alsacard=hw:0 --with-alsamixer=Master --with-fbdev=/dev/fb0 --with-lcddev=/dev/null --with-amlogic"
DEPENDS_append_pn-enigma2 = " libdvbcsa linkdroid-libamcodec-${MACHINE} linkdroid-libamadec-${MACHINE} gstreamer1.0 gstreamer1.0-plugins-base"
EXTRA_OECONF_append_pn-tuxtxt-enigma2 = " --with-fbdev=/dev/fb0"
DEPENDS_append_pn-enigma2-plugin-systemplugins-servicemp3 = " linkdroid-libamcodec-${MACHINE} "
CC_append_pn-showiframe = " -DHAVE_AMLOGIC -I${STAGING_INCDIR}/amlogic/amcodec -lamcodec -lgstpbutils-1.0 -lgstreamer-1.0 -lgobject-2.0 -lglib-2.0"
DEPENDS_append_pn-showiframe = " linkdroid-libamcodec-${MACHINE} gstreamer1.0 gstreamer1.0-libav gstreamer1.0-plugins-base gstreamer1.0-plugins-good gstreamer1.0-plugins-ugly"

DISTRO_FEATURES_remove = "x11 wayland"

IMAGEDIR ?= "${MACHINE}"

IMAGE_CMD_amlogicsdimg_append = "\
    mkdir -p ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}; \
    mv ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.img ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/${IMAGE_NAME}.img; \
    echo ${IMAGE_NAME} > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/imageversion; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.zip; \
    cd ${DEPLOY_DIR_IMAGE}; \
    zip ${IMAGE_NAME}_sdcard.zip ${IMAGEDIR}/*; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.ext4; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.img; \
    rm -Rf ${IMAGEDIR}; \
    "

# We know about rtc
require conf/machine/include/build-extras.inc
