/// RadarChartWidget
/// -----------------------------------------------
/// Widget que muestra un gráfico radar para visualizar
/// el progreso del usuario en distintas categorías,
/// basado en las tareas completadas.
/// -----------------------------------------------
/// Utiliza la librería fl_chart para mostrar el radar.
/// Cada categoría tiene un valor entre 0 y 5 basado en
/// la proporción de tareas completadas.
/// -----------------------------------------------
/// @author DMIAPER
/// @version 1.0.0
/// @date 27/05/2025
library;

import 'package:flutter/material.dart';
import 'package:fl_chart/fl_chart.dart';
import '../models/task_model.dart';
import '../models/category_model.dart';

class RadarChartWidget extends StatelessWidget {
  final List<Task> tasks;
  final List<Category> categories;

  const RadarChartWidget({
    super.key,
    required this.tasks,
    required this.categories,
  });

  /// Calcula el puntaje total de una categoría basado en puntos (normalizado 0-5)
  double _calculateNormalizedScore(int categoryId) {
    final catTasks = tasks.where((t) => t.categoryId == categoryId);
    if (catTasks.isEmpty) return 0;
    final totalPoints = catTasks.fold<int>(0, (sum, t) => sum + t.points);
    final normalized = totalPoints.clamp(0, 100) / 100.0 * 5.0;
    return normalized;
  }

  @override
  Widget build(BuildContext context) {
    final data =
        categories.map((c) => _calculateNormalizedScore(c.id)).toList();

    return SizedBox(
      height: 300,
      child: RadarChart(
        RadarChartData(
          radarShape: RadarShape.polygon,
          tickCount: 5,
          radarBorderData: const BorderSide(color: Colors.transparent),
          ticksTextStyle: const TextStyle(color: Colors.white, fontSize: 10),
          radarBackgroundColor: Colors.transparent,
          gridBorderData: const BorderSide(color: Colors.white, width: 0.5),
          titleTextStyle: const TextStyle(
            color: Colors.white,
            fontSize: 14,
            fontWeight: FontWeight.bold,
          ),
          titlePositionPercentageOffset: 0.15,
          getTitle: (index, angle) {
            if (index >= 0 && index < categories.length) {
              return RadarChartTitle(
                text: categories[index].name,
                angle: angle,
              );
            }
            return const RadarChartTitle(text: '');
          },
          dataSets: [
            RadarDataSet(
              dataEntries: List.generate(
                categories.length,
                (index) => RadarEntry(value: data[index]),
              ),
              fillColor: Colors.deepPurple.withOpacity(0.4),
              borderColor: Colors.deepPurple,
              entryRadius: 3,
              borderWidth: 2,
            ),
          ],
        ),
      ),
    );
  }
}
