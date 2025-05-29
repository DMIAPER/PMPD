/// Category model
/// -----------------------------------------------
/// Representa una categoría para las tareas.
/// Contiene id, nombre y color.
/// -----------------------------------------------
/// @author DMIAPER
/// @version 1.0.0
/// @date 27/05/2025
library;

class Category {
  int id;
  String name;
  String color;

  Category({required this.id, required this.name, required this.color});

  // Convierte el objeto Category a un Map para la BD si es necesario
  Map<String, dynamic> toMap() {
    return {'id': id, 'name': name, 'color': color};
  }

  // Crea un objeto Category a partir de un Map
  factory Category.fromMap(Map<String, dynamic> map) {
    return Category(
      id: map['id'] as int,
      name: map['name'] as String,
      color: map['color'] as String,
    );
  }
}
