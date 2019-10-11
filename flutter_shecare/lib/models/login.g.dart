// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'login.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Login _$LoginFromJson(Map<String, dynamic> json) {
  return Login()
    ..code = json['code'] as num
    ..message = json['message'] as String
    ..taskVersion = json['taskVersion'] as num
    ..versionJson = json['versionJson'] as String
    ..startVersion = json['startVersion'] as num
    ..authToken = json['authToken'] as String;
}

Map<String, dynamic> _$LoginToJson(Login instance) => <String, dynamic>{
      'code': instance.code,
      'message': instance.message,
      'taskVersion': instance.taskVersion,
      'versionJson': instance.versionJson,
      'startVersion': instance.startVersion,
      'authToken': instance.authToken
    };
