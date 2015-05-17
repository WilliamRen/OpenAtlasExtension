//
// Copyright 2006 The Android Open Source Project
//
// Build resource files from raw assets.
//
#include "Resourcehack.h"


void hack_getVersionName(Bundle* bundle){
//  return ;
  fprintf(stderr, "hack version ibundle->getAndroidManifestFile()X%s,  \n",bundle->getAndroidManifestFile());
  String8 srcFile(bundle->getAndroidManifestFile());
  fprintf(stderr, "hack version ibundle->getAndroidManifestFile() %s , %s 1\n",srcFile.getPathLeaf().string(),  srcFile.getPathDir().string());
  AaptFile *mAaptFile=new AaptFile(srcFile.getPathLeaf(), AaptGroupEntry(), srcFile.getPathDir());
  fprintf(stderr, "hack version ibundle->getAndroidManifestFile()2\n");
  const sp<AaptFile> manifestFile(mAaptFile);
  fprintf(stderr, "hack version ibundle->getAndroidManifestFile()3\n");
  String8 manifestPath(bundle->getAndroidManifestFile());
  fprintf(stderr, "hack version dump info ..get default versionName%s\n",manifestPath.string());
  fprintf(stderr, "hack version ibundle->getAndroidManifestFile()4\n");
  // Generate final compiled manifest file.
  //manifestFile->clearData();
  sp<XMLNode> root = XMLNode::parse(bundle->getAndroidManifestFile());
  fprintf(stderr, "hack version ibundle->getAndroidManifestFile()6\n");
  if (root == NULL) {
    if(!access(bundle->getAndroidManifestFile(),0)){}else{
      fprintf(stderr, "no  found 7\n");
    }
    fprintf(stderr, "no node 7\n");
      return ;
  }
 hack_massageManifest(root);

// root = root->searchElement(String16(), String16("manifest"));
//
// const XMLNode::attribute_entry* attrlocal = root->getAttribute(
//         String16(RESOURCES_ANDROID_NAMESPACE), String16("versionName"));
// if (attrlocal != NULL) {
//   fprintf(stderr, "hack version dump info ..get default versionName%s\n",strdup(String8(attrlocal->string).string()));
//   char * versionNameMisc=strdup(String8(attrlocal->string).string());//bunny
//   if(strlen(versionNameMisc)>5){
//     char resOffset[64]={0};
//     strncpy(resOffset,versionNameMisc+strlen(versionNameMisc)-4,4);
//     if(resOffset[0]=='0'&&resOffset[1]=='x'){
//       pkgIdOffset=strtol(resOffset,NULL,16);
//     }
//     fprintf(stderr, "hack version is ok,found new version packageID %s \n",resOffset);
//   }else{
//     fprintf(stderr, "hack version is failed,versionName should endwith 0xXX  \n");
//
//   }
//
//
//
// }
  //delete(mAaptFile);
  fprintf(stderr, "hack version ibundle->getAndroidManifestFile()7\n");

}
void hack_massageManifest( sp<XMLNode> root)
{
    root = root->searchElement(String16(), String16("manifest"));

    const XMLNode::attribute_entry* attrlocal = root->getAttribute(
            String16(RESOURCES_ANDROID_NAMESPACE), String16("versionName"));
    if (attrlocal != NULL) {
      fprintf(stderr, "hack version dump info ..get default versionName%s\n",strdup(String8(attrlocal->string).string()));
      char * versionNameMisc=strdup(String8(attrlocal->string).string());//bunny
      if(strlen(versionNameMisc)>5){
        char resOffset[64]={0};
        strncpy(resOffset,versionNameMisc+strlen(versionNameMisc)-4,4);
        if(resOffset[0]=='0'&&resOffset[1]=='x'){
          pkgIdOffset=strtol(resOffset,NULL,16);
          isUpdatePkgId=1;
        }
        fprintf(stderr, "hack version is ok,found new version packageID %s \n",resOffset);
      }else{
        fprintf(stderr, "hack version is failed,versionName should endwith 0xXX  \n");

      }



    }
    // if (!addTagAttribute(root, RESOURCES_ANDROID_NAMESPACE, "versionName",
    //         "0x7f", errorOnFailedInsert, true)) {
    //     return UNKNOWN_ERROR;
    // } else {
    //     const XMLNode::attribute_entry* attr = root->getAttribute(
    //             String16(RESOURCES_ANDROID_NAMESPACE), String16("versionName"));
    //     if (attr != NULL) {
    //       fprintf(stderr, "hack version dump info... %s\n",strdup(String8(attr->string).string()));
    //         bundle->setVersionName(strdup(String8(attr->string).string()));
    //     }
    // }



}
