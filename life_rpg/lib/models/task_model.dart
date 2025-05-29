/// Task model
/// -----------------------------------------------
/// Representa una tarea dentro de una categoría.
/// Incluye puntos de experiencia para calcular el nivel.
/// -----------------------------------------------
/// @author DMIAPER (Diógenes Miaja Pérez)
/// @version 1.0.0
/// @date 27/05/2025
library;

class Task {
  int? id;
  String title;
  int categoryId;
  int points;

  Task({
    this.id,
    required this.title,
    required this.categoryId,
    this.points = 0,
  });

  /// Calcula el nivel según los puntos (1 nivel cada 10 puntos)
  int get level => (points ~/ 10) + 1;

  /// Convierte el objeto a un Map para la base de datos
  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'title': title,
      'categoryId': categoryId,
      'points': points,
    };
  }

  /// Crea una tarea a partir de un Map (registro de la base de datos)
  factory Task.fromMap(Map<String, dynamic> map) {
    return Task(
      id: map['id'] as int?,
      title: map['title'] as String,
      categoryId: map['categoryId'] as int,
      points: map['points'] as int? ?? 0,
    );
  }
}
