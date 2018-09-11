# Copyright (c) 2015 Riverbank Computing Limited <info@riverbankcomputing.com>
# 
# This file is part of PyQt4.
# 
# This file may be used under the terms of the GNU General Public License
# version 3.0 as published by the Free Software Foundation and appearing in
# the file LICENSE included in the packaging of this file.  Please review the
# following information to ensure the GNU General Public License version 3.0
# requirements will be met: http://www.gnu.org/copyleft/gpl.html.
# 
# If you do not wish to use this file under the terms of the GPL version 3.0
# then you may purchase a commercial license.  For more information contact
# info@riverbankcomputing.com.
# 
# This file is provided AS IS with NO WARRANTY OF ANY KIND, INCLUDING THE
# WARRANTY OF DESIGN, MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.
#
# This module is intended to be used by the configuration scripts of extension
# modules that %Import PyQt4 modules.


import sipconfig


# These are installation specific values created when PyQt4 was configured.
_pkg_config = {
    'pyqt_bin_dir':      'X:\\Python37',
    'pyqt_config_args':  '-w --confirm-license',
    'pyqt_mod_dir':      'X:\\Python37\\Lib\\site-packages\\PyQt4',
    'pyqt_modules':      'QtCore QtGui QtHelp QtMultimedia QtNetwork QtDeclarative QtScript QtScriptTools QtXml QtOpenGL QtSql QtSvg QtTest QtWebKit QtXmlPatterns phonon QtDesigner QAxContainer',
    'pyqt_sip_dir':      'X:\\Python37\\sip\\PyQt4',
    'pyqt_sip_flags':    '-x VendorID -t WS_WIN -x PyQt_NoPrintRangeBug -t Qt_4_8_6 -g',
    'pyqt_version':      0x040b04,
    'pyqt_version_str':  '4.11.4',
    'qt_archdata_dir':   'X:\\Qt487-vc14-x64',
    'qt_data_dir':       'X:\\Qt487-vc14-x64',
    'qt_dir':            'X:\\Qt487-vc14-x64',
    'qt_edition':        'free',
    'qt_framework':      0,
    'qt_inc_dir':        'X:\\Qt487-vc14-x64\\include',
    'qt_lib_dir':        'X:\\Qt487-vc14-x64\\lib',
    'qt_threaded':       1,
    'qt_version':        0x040807,
    'qt_winconfig':      'shared'
}

_default_macros = {
    'AIX_SHLIB':                '',
    'AR':                       '',
    'CC':                       'cl',
    'CFLAGS':                   '-nologo -Zm200 -Zc:wchar_t- -FS',
    'CFLAGS_APP':               '',
    'CFLAGS_CONSOLE':           '',
    'CFLAGS_DEBUG':             '-Zi -MDd',
    'CFLAGS_EXCEPTIONS_OFF':    '',
    'CFLAGS_EXCEPTIONS_ON':     '',
    'CFLAGS_MT':                '',
    'CFLAGS_MT_DBG':            '',
    'CFLAGS_MT_DLL':            '',
    'CFLAGS_MT_DLLDBG':         '',
    'CFLAGS_RELEASE':           '-O2 -MD',
    'CFLAGS_RTTI_OFF':          '',
    'CFLAGS_RTTI_ON':           '',
    'CFLAGS_SHLIB':             '',
    'CFLAGS_STL_OFF':           '',
    'CFLAGS_STL_ON':            '',
    'CFLAGS_THREAD':            '',
    'CFLAGS_WARN_OFF':          '-W0',
    'CFLAGS_WARN_ON':           '-W3',
    'CHK_DIR_EXISTS':           'if not exist',
    'CONFIG':                   'qt warn_on release incremental flat link_prl precompile_header autogen_precompile_source copy_dir_files debug_and_release debug_and_release_target embed_manifest_dll embed_manifest_exe',
    'COPY':                     'copy /y',
    'CXX':                      'cl',
    'CXXFLAGS':                 '-nologo -Zm200 -Zc:wchar_t- -FS',
    'CXXFLAGS_APP':             '',
    'CXXFLAGS_CONSOLE':         '',
    'CXXFLAGS_DEBUG':           '-Zi -MDd',
    'CXXFLAGS_EXCEPTIONS_OFF':  '',
    'CXXFLAGS_EXCEPTIONS_ON':   '-EHsc',
    'CXXFLAGS_MT':              '',
    'CXXFLAGS_MT_DBG':          '',
    'CXXFLAGS_MT_DLL':          '',
    'CXXFLAGS_MT_DLLDBG':       '',
    'CXXFLAGS_RELEASE':         '-O2 -MD',
    'CXXFLAGS_RTTI_OFF':        '',
    'CXXFLAGS_RTTI_ON':         '-GR',
    'CXXFLAGS_SHLIB':           '',
    'CXXFLAGS_STL_OFF':         '',
    'CXXFLAGS_STL_ON':          '-EHsc',
    'CXXFLAGS_THREAD':          '',
    'CXXFLAGS_WARN_OFF':        '-W0',
    'CXXFLAGS_WARN_ON':         '-W3 -w34100 -w34189',
    'DEFINES':                  'UNICODE WIN32',
    'DEL_FILE':                 'del',
    'EXTENSION_PLUGIN':         '',
    'EXTENSION_SHLIB':          '',
    'INCDIR':                   '',
    'INCDIR_OPENGL':            '',
    'INCDIR_QT':                'X:\\Qt487-vc14-x64\\include',
    'INCDIR_X11':               '',
    'LFLAGS':                   '/NOLOGO /DYNAMICBASE /NXCOMPAT',
    'LFLAGS_CONSOLE':           '/SUBSYSTEM:CONSOLE',
    'LFLAGS_CONSOLE_DLL':       '',
    'LFLAGS_DEBUG':             '/DEBUG',
    'LFLAGS_DLL':               '/DLL',
    'LFLAGS_OPENGL':            '',
    'LFLAGS_PLUGIN':            '',
    'LFLAGS_RELEASE':           '/INCREMENTAL:NO',
    'LFLAGS_RPATH':             '',
    'LFLAGS_SHLIB':             '',
    'LFLAGS_SONAME':            '',
    'LFLAGS_THREAD':            '',
    'LFLAGS_WINDOWS':           '/SUBSYSTEM:WINDOWS',
    'LFLAGS_WINDOWS_DLL':       '',
    'LIB':                      'lib /NOLOGO',
    'LIBDIR':                   '',
    'LIBDIR_OPENGL':            '',
    'LIBDIR_QT':                'X:\\Qt487-vc14-x64\\lib',
    'LIBDIR_X11':               '',
    'LIBS':                     '',
    'LIBS_CONSOLE':             '',
    'LIBS_CORE':                'kernel32.lib user32.lib shell32.lib uuid.lib ole32.lib advapi32.lib ws2_32.lib',
    'LIBS_GUI':                 'gdi32.lib comdlg32.lib oleaut32.lib imm32.lib winmm.lib winspool.lib ws2_32.lib ole32.lib user32.lib advapi32.lib',
    'LIBS_NETWORK':             'ws2_32.lib',
    'LIBS_OPENGL':              'glu32.lib opengl32.lib gdi32.lib user32.lib',
    'LIBS_RT':                  '',
    'LIBS_RTMT':                '',
    'LIBS_THREAD':              '',
    'LIBS_WEBKIT':              '',
    'LIBS_WINDOWS':             '',
    'LIBS_X11':                 '',
    'LINK':                     'link',
    'LINK_SHLIB':               '',
    'LINK_SHLIB_CMD':           '',
    'MAKEFILE_GENERATOR':       'MSBUILD',
    'MKDIR':                    'mkdir',
    'MOC':                      'X:\\Qt487-vc14-x64\\bin\\\\moc.exe',
    'RANLIB':                   '',
    'RPATH':                    '',
    'STRIP':                    ''
}


class Configuration(sipconfig.Configuration):
    """The class that represents PyQt configuration values.
    """
    def __init__(self, sub_cfg=None):
        """Initialise an instance of the class.

        sub_cfg is the list of sub-class configurations.  It should be None
        when called normally.
        """
        if sub_cfg:
            cfg = sub_cfg
        else:
            cfg = []

        cfg.append(_pkg_config)

        sipconfig.Configuration.__init__(self, cfg)


class QtCoreModuleMakefile(sipconfig.SIPModuleMakefile):
    """The Makefile class for modules that %Import QtCore.
    """
    def __init__(self, *args, **kw):
        """Initialise an instance of a module Makefile.
        """
        if "qt" not in kw:
            kw["qt"] = ["QtCore"]

        sipconfig.SIPModuleMakefile.__init__(self, *args, **kw)


class QtGuiModuleMakefile(QtCoreModuleMakefile):
    """The Makefile class for modules that %Import QtGui.
    """
    def __init__(self, *args, **kw):
        """Initialise an instance of a module Makefile.
        """
        if "qt" not in kw:
            kw["qt"] = ["QtCore", "QtGui"]

        QtCoreModuleMakefile.__init__(self, *args, **kw)


class QtHelpModuleMakefile(QtGuiModuleMakefile):
    """The Makefile class for modules that %Import QtHelp.
    """
    def __init__(self, *args, **kw):
        """Initialise an instance of a module Makefile.
        """
        if "qt" not in kw:
            kw["qt"] = ["QtCore", "QtGui", "QtHelp"]

        QtGuiModuleMakefile.__init__(self, *args, **kw)


class QtMultimediaModuleMakefile(QtGuiModuleMakefile):
    """The Makefile class for modules that %Import QtMultimedia.
    """
    def __init__(self, *args, **kw):
        """Initialise an instance of a module Makefile.
        """
        if "qt" not in kw:
            kw["qt"] = ["QtCore", "QtGui", "QtMultimedia"]

        QtGuiModuleMakefile.__init__(self, *args, **kw)


class QtNetworkModuleMakefile(QtCoreModuleMakefile):
    """The Makefile class for modules that %Import QtNetwork.
    """
    def __init__(self, *args, **kw):
        """Initialise an instance of a module Makefile.
        """
        if "qt" not in kw:
            kw["qt"] = ["QtCore", "QtNetwork"]

        QtCoreModuleMakefile.__init__(self, *args, **kw)


class QtDeclarativeModuleMakefile(QtNetworkModuleMakefile):
    """The Makefile class for modules that %Import QtDeclarative.
    """
    def __init__(self, *args, **kw):
        """Initialise an instance of a module Makefile.
        """
        if "qt" not in kw:
            kw["qt"] = ["QtCore", "QtGui", "QtNetwork", "QtDeclarative"]

        QtNetworkModuleMakefile.__init__(self, *args, **kw)


class QtAssistantModuleMakefile(QtNetworkModuleMakefile):
    """The Makefile class for modules that %Import QtAssistant.
    """
    def __init__(self, *args, **kw):
        """Initialise an instance of a module Makefile.
        """
        if "qt" not in kw:
            kw["qt"] = ["QtCore", "QtGui", "QtNetwork", "QtAssistant"]

        QtNetworkModuleMakefile.__init__(self, *args, **kw)


class QtOpenGLModuleMakefile(QtGuiModuleMakefile):
    """The Makefile class for modules that %Import QtOpenGL.
    """
    def __init__(self, *args, **kw):
        """Initialise an instance of a module Makefile.
        """
        kw["opengl"] = 1

        if "qt" not in kw:
            kw["qt"] = ["QtCore", "QtGui", "QtOpenGL"]

        QtGuiModuleMakefile.__init__(self, *args, **kw)


class QtScriptModuleMakefile(QtCoreModuleMakefile):
    """The Makefile class for modules that %Import QtScript.
    """
    def __init__(self, *args, **kw):
        """Initialise an instance of a module Makefile.
        """
        if "qt" not in kw:
            kw["qt"] = ["QtCore", "QtScript"]

        QtCoreModuleMakefile.__init__(self, *args, **kw)


class QtScriptToolsModuleMakefile(QtScriptModuleMakefile):
    """The Makefile class for modules that %Import QtScriptTools.
    """
    def __init__(self, *args, **kw):
        """Initialise an instance of a module Makefile.
        """
        if "qt" not in kw:
            kw["qt"] = ["QtCore", "QtGui", "QtScript", "QtScriptTools"]

        QtScriptModuleMakefile.__init__(self, *args, **kw)


class QtSqlModuleMakefile(QtGuiModuleMakefile):
    """The Makefile class for modules that %Import QtSql.
    """
    def __init__(self, *args, **kw):
        """Initialise an instance of a module Makefile.
        """
        if "qt" not in kw:
            kw["qt"] = ["QtCore", "QtGui", "QtSql"]

        QtGuiModuleMakefile.__init__(self, *args, **kw)


class QtSvgModuleMakefile(QtGuiModuleMakefile):
    """The Makefile class for modules that %Import QtSvg.
    """
    def __init__(self, *args, **kw):
        """Initialise an instance of a module Makefile.
        """
        if "qt" not in kw:
            kw["qt"] = ["QtCore", "QtGui", "QtSvg"]

        QtGuiModuleMakefile.__init__(self, *args, **kw)


class QtTestModuleMakefile(QtGuiModuleMakefile):
    """The Makefile class for modules that %Import QtTest.
    """
    def __init__(self, *args, **kw):
        """Initialise an instance of a module Makefile.
        """
        if "qt" not in kw:
            kw["qt"] = ["QtCore", "QtGui", "QtTest"]

        QtGuiModuleMakefile.__init__(self, *args, **kw)


class QtWebKitModuleMakefile(QtNetworkModuleMakefile):
    """The Makefile class for modules that %Import QtWebKit.
    """
    def __init__(self, *args, **kw):
        """Initialise an instance of a module Makefile.
        """
        if "qt" not in kw:
            kw["qt"] = ["QtCore", "QtGui", "QtNetwork", "QtWebKit"]

        QtNetworkModuleMakefile.__init__(self, *args, **kw)


class QtXmlModuleMakefile(QtCoreModuleMakefile):
    """The Makefile class for modules that %Import QtXml.
    """
    def __init__(self, *args, **kw):
        """Initialise an instance of a module Makefile.
        """
        if "qt" not in kw:
            kw["qt"] = ["QtCore", "QtXml"]

        QtCoreModuleMakefile.__init__(self, *args, **kw)


class QtXmlPatternsModuleMakefile(QtCoreModuleMakefile):
    """The Makefile class for modules that %Import QtXmlPatterns.
    """
    def __init__(self, *args, **kw):
        """Initialise an instance of a module Makefile.
        """
        if "qt" not in kw:
            kw["qt"] = ["QtCore", "QtXmlPatterns"]

        QtCoreModuleMakefile.__init__(self, *args, **kw)


class phononModuleMakefile(QtGuiModuleMakefile):
    """The Makefile class for modules that %Import phonon.
    """
    def __init__(self, *args, **kw):
        """Initialise an instance of a module Makefile.
        """
        if "qt" not in kw:
            kw["qt"] = ["QtCore", "QtGui", "phonon"]

        QtGuiModuleMakefile.__init__(self, *args, **kw)


class QtDesignerModuleMakefile(QtGuiModuleMakefile):
    """The Makefile class for modules that %Import QtDesigner.
    """
    def __init__(self, *args, **kw):
        """Initialise an instance of a module Makefile.
        """
        if "qt" not in kw:
            kw["qt"] = ["QtCore", "QtGui", "QtDesigner"]

        QtGuiModuleMakefile.__init__(self, *args, **kw)


class QAxContainerModuleMakefile(QtGuiModuleMakefile):
    """The Makefile class for modules that %Import QAxContainer.
    """
    def __init__(self, *args, **kw):
        """Initialise an instance of a module Makefile.
        """
        if "qt" not in kw:
            kw["qt"] = ["QtCore", "QtGui", "QAxContainer"]

        QtGuiModuleMakefile.__init__(self, *args, **kw)


class QtDBusModuleMakefile(QtCoreModuleMakefile):
    """The Makefile class for modules that %Import QtDBus.
    """
    def __init__(self, *args, **kw):
        """Initialise an instance of a module Makefile.
        """
        if "qt" not in kw:
            kw["qt"] = ["QtCore", "QtDBus"]

        QtCoreModuleMakefile.__init__(self, *args, **kw)
