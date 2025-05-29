/// SkillProgressList
/// -----------------------------------------------
/// Widget que muestra una lista de barras de progreso
/// representando el avance en cada habilidad (categoría)
/// basado en la proporción de tareas completadas.
/// Muestra el nombre de la categoría y porcentaje completado,
/// junto con una barra de progreso visual.
/// -----------------------------------------------
/// @author DMIAPER
/// @version 1.0.0
/// @date 27/05/2025
library;

import 'package:flutter/material.dart';
import '../models/task_model.dart';
import '../models/category_model.dart';

class SkillProgressList extends StatelessWidget {
  final List<Task> tasks;
  final List<Category> categories;

  const SkillProgressList({
    super.key,
    required this.tasks,
    required this.categories,
  });

  /// Retorna la suma de puntos para una categoría
  double _calculateProgress(int categoryId) {
    final catTasks = tasks.where((t) => t.categoryId == categoryId);
    if (catTasks.isEmpty) return 0.0;
    final totalPoints = catTasks.fold<int>(0, (sum, t) => sum + t.points);
    return (totalPoints.clamp(0, 100)) / 100.0;
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children:
          categories.map((cat) {
            final progress = _calculateProgress(cat.id);
            final percentage = (progress * 100).toInt();

            return Padding(
              padding: const EdgeInsets.symmetric(vertical: 4),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  // Título: nombre + porcentaje
                  Text(
                    "${cat.name} ($percentage%)",
                    style: const TextStyle(fontWeight: FontWeight.bold),
                  ),
                  const SizedBox(height: 4),
                  // Barra de progreso
                  LinearProgressIndicator(
                    value: progress,
                    color: Colors.deepPurple,
                    backgroundColor: Colors.deepPurple[100],
                    minHeight: 8,
                  ),
                ],
              ),
            );
          }).toList(),
    );
  }
}
