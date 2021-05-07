require recipes-mediacenter/kodi/stb-kodi_${PV}.bb

PROVIDES += "virtual/kodi"
RPROVIDES_${PN} += "virtual/kodi"
PROVIDES += "kodi"
RPROVIDES_${PN} += "kodi"

DEPENDS_append = " amlogic-libamadec amlogic-libamcodec amlogic-libamavutils"

EXTRA_OECONF += " \
    --with-platform=aml-aarch64 \
    --with-ffmpeg=stb \
"

COMPATIBLE_MACHINE = "^(k1pro|k2pro|k2prov2|k3pro|k1plus|k1plusv2)$"
