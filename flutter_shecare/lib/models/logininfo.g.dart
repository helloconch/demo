// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'logininfo.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Logininfo _$LogininfoFromJson(Map<String, dynamic> json) {
  return Logininfo()
    ..code = json['code'] as num
    ..message = json['message'] as String
    ..taskVersion = json['taskVersion'] as num
    ..versionJson = json['versionJson'] as String
    ..startVersion = json['startVersion'] as num
    ..authToken = json['authToken'] as String;
}

Map<String, dynamic> _$LogininfoToJson(Logininfo instance) => <String, dynamic>{
      'code': instance.code,
      'message': instance.message,
      'taskVersion': instance.taskVersion,
      'versionJson': instance.versionJson,
      'startVersion': instance.startVersion,
      'authToken': instance.authToken
    };
