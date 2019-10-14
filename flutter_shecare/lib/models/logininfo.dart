import 'package:json_annotation/json_annotation.dart';

part 'logininfo.g.dart';

@JsonSerializable()
class Logininfo {
    Logininfo();

    num code;
    String message;
    num taskVersion;
    String versionJson;
    num startVersion;
    String authToken;
    
    factory Logininfo.fromJson(Map<String,dynamic> json) => _$LogininfoFromJson(json);
    Map<String, dynamic> toJson() => _$LogininfoToJson(this);
}
