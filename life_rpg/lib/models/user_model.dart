/// lib/models/user_model.dart
/// User Model
/// -----------------------------------------------
/// Modelo de datos para usuarios.
/// Incluye información de perfil y progreso.
/// -----------------------------------------------
/// @author DMIAPER
/// @version 1.0.0
/// @date 27/05/2025
library;

class User {
  int? id;
  String name;
  String password;
  String avatar;
  int totalXP;

  User({
    this.id,
    required this.name,
    required this.password,
    String? avatar,
    this.totalXP = 0,
  }) : avatar = avatar ?? 'assets/images/default_avatar.png';

  // Convierte el objeto User a un Map para la BD
  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'name': name,
      'password': password,
      'avatar': avatar,
      'totalXP': totalXP,
    };
  }

  // Crea un objeto User a partir de un Map (registro BD)
  factory User.fromMap(Map<String, dynamic> map) {
    return User(
      id: map['id'] as int?,
      name: map['name'] as String,
      password: map['password'] as String,
      avatar: map['avatar'] as String?,
      totalXP: map['totalXP'] as int? ?? 0,
    );
  }
}
