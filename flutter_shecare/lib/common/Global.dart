import 'package:shared_preferences/shared_preferences.dart';

class Global {
  static bool get isRelease => bool.fromEnvironment('dart.vm.product');

  static void save(String key, String value) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    await prefs.setString(key, value);
  }

  static Future<String> get(String key) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    return await prefs.getString(key);
  }
}
