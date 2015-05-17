//
// Copyright 2006 The Android Open Source Project
//
// Build resource files from raw assets.
//
#ifndef __RESIURCES_HACK_H__
#define __RESIURCES_HACK_H__

#include "AaptAssets.h"
#include "AaptUtil.h"
#include "AaptXml.h"
#include "CacheUpdater.h"
#include "CrunchCache.h"
#include "FileFinder.h"
#include "Images.h"
#include "IndentPrinter.h"
#include "Main.h"
#include "ResourceTable.h"
#include "StringPool.h"
#include "Symbol.h"
#include "WorkQueue.h"

#include "XMLNode.h"
#include "Main.h"
#include <androidfw/PkgConfig.h>
#include <algorithm>

void hack_massageManifest( sp<XMLNode> root);
void hack_getVersionName(Bundle* bundle);
#endif
