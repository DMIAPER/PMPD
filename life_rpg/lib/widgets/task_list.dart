/// Task list widget
/// -----------------------------------------------
/// este widget muestra una lista de tareas con sus categorías y
/// permite incrementar puntos de experiencia o eliminar tareas.
/// -----------------------------------------------
/// @author DMIAPER (Diógenes Miaja Pérez)
/// @version 1.0.0
/// @date 27/05/2025
library;

import 'package:flutter/material.dart';
import '../models/task_model.dart';
import '../models/category_model.dart';

class TaskList extends StatelessWidget {
  final List<Task> tasks;
  final List<Category> categories;
  final Function(Task) onIncrementXP;
  final Function(Task) onDelete;

  const TaskList({
    super.key,
    required this.tasks,
    required this.categories,
    required this.onIncrementXP,
    required this.onDelete,
  });

  Category _getCategory(int categoryId) {
    return categories.firstWhere((cat) => cat.id == categoryId);
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children:
          tasks.map((task) {
            final category = _getCategory(task.categoryId);
            return Card(
              child: ListTile(
                leading: CircleAvatar(
                  backgroundColor: Color(
                    int.parse("0xff${category.color.substring(1)}"),
                  ),
                  child: Text(task.level.toString()),
                ),
                title: Text(task.title),
                subtitle: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text("Puntos: ${task.points}"),
                    LinearProgressIndicator(
                      value: task.points / 100,
                      backgroundColor: Colors.grey[300],
                      color: Theme.of(context).primaryColor,
                    ),
                  ],
                ),
                trailing: Row(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    IconButton(
                      icon: const Icon(Icons.add),
                      onPressed: () => onIncrementXP(task),
                      tooltip: 'Agregar 1 punto de experiencia',
                    ),
                    IconButton(
                      icon: const Icon(Icons.delete),
                      onPressed: () => onDelete(task),
                      tooltip: 'Eliminar tarea',
                    ),
                  ],
                ),
              ),
            );
          }).toList(),
    );
  }
}
