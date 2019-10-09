import 'package:json_annotation/json_annotation.dart';

part 'login.g.dart';

@JsonSerializable()
class Login {
    Login();

    num code;
    String message;
    num taskVersion;
    String versionJson;
    num startVersion;
    String authToken;
    
    factory Login.fromJson(Map<String,dynamic> json) => _$LoginFromJson(json);
    Map<String, dynamic> toJson() => _$LoginToJson(this);
}
