/// DashboardScreen
/// -----------------------------------------------
/// Pantalla principal del usuario, muestra:
/// - Avatar y experiencia
/// - RadarChart del progreso por categoría
/// - Barras de progreso por habilidad
/// - Lista de tareas
/// -----------------------------------------------
/// Permite agregar nuevas tareas y cambiar avatar.
/// -----------------------------------------------
/// @author DMIAPER
/// @version 1.0.1
/// @date 28/05/2025

library;

import 'package:flutter/material.dart';
import 'package:flutter/services.dart'; // <- Para SystemNavigator.pop()
import 'dart:io' show Platform; // <- Para detección de plataforma
import '../../models/category_model.dart';
import '../../models/user_model.dart';
import '../../models/task_model.dart';
import '../../widgets/radar_chart_widget.dart';
import '../../widgets/avatar_selector.dart';
import '../../widgets/player_info_card.dart';
import '../../widgets/skill_progress_list.dart';
import '../../widgets/task_list.dart';
import '../../database/database_helper.dart';
import '../auth/login_register.dart'; // <- Importa la pantalla de login

class DashboardScreen extends StatefulWidget {
  final User user;

  const DashboardScreen({super.key, required this.user});

  @override
  State<DashboardScreen> createState() => _DashboardScreenState();
}

class _DashboardScreenState extends State<DashboardScreen> {
  List<Category> categories = [];
  List<Task> tasks = [];
  late String selectedAvatar;

  @override
  void initState() {
    super.initState();
    selectedAvatar = widget.user.avatar;
    _loadInitialData();
  }

  Future<void> _loadInitialData() async {
    categories = [
      Category(id: 1, name: "Salud", color: "#ff4c4c"),
      Category(id: 2, name: "Productividad", color: "#4c6bff"),
      Category(id: 3, name: "Creatividad", color: "#ffb74c"),
    ];

    tasks = await DatabaseHelper.instance.getTasks();
    if (!mounted) return;
    setState(() {});
  }

  Future<void> _addNewTask() async {
    String newTask = "";
    int selectedCategory = categories.first.id;

    final result = await showDialog<bool>(
      context: context,
      builder: (context) {
        String dialogTask = newTask;
        int dialogCategory = selectedCategory;

        return StatefulBuilder(
          builder:
              (context, setStateDialog) => AlertDialog(
                title: const Text("Nueva tarea"),
                content: Column(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    TextField(
                      onChanged: (value) => dialogTask = value,
                      decoration: const InputDecoration(
                        labelText: "Título de la tarea",
                      ),
                    ),
                    const SizedBox(height: 10),
                    DropdownButton<int>(
                      value: dialogCategory,
                      items:
                          categories
                              .map(
                                (c) => DropdownMenuItem(
                                  value: c.id,
                                  child: Text(c.name),
                                ),
                              )
                              .toList(),
                      onChanged: (value) {
                        if (value != null) {
                          setStateDialog(() {
                            dialogCategory = value;
                          });
                        }
                      },
                    ),
                  ],
                ),
                actions: [
                  TextButton(
                    onPressed: () => Navigator.pop(context, false),
                    child: const Text("Cancelar"),
                  ),
                  TextButton(
                    onPressed: () {
                      if (dialogTask.trim().isNotEmpty) {
                        newTask = dialogTask;
                        selectedCategory = dialogCategory;
                        Navigator.pop(context, true);
                      }
                    },
                    child: const Text("Agregar"),
                  ),
                ],
              ),
        );
      },
    );

    if (result == true && mounted) {
      Task newTaskObj = Task(
        title: newTask,
        categoryId: selectedCategory,
        points: 0,
      );

      int newId = await DatabaseHelper.instance.insertTask(newTaskObj);

      if (newId != -1) {
        setState(() {
          tasks.add(
            Task(
              id: newId,
              title: newTask,
              categoryId: selectedCategory,
              points: 0,
            ),
          );
        });
      } else {
        ScaffoldMessenger.of(
          context,
        ).showSnackBar(const SnackBar(content: Text('Error al agregar tarea')));
      }
    }
  }

  Future<void> _incrementTaskXP(Task task) async {
    if (task.id == null) return;

    if (task.points < 100) {
      task.points++;
      await DatabaseHelper.instance.updateTask(task);

      if (!mounted) return;
      setState(() {});
    }
  }

  Future<void> _deleteTask(Task task) async {
    if (task.id != null) {
      await DatabaseHelper.instance.deleteTask(task.id!);
      if (!mounted) return;
      setState(() {
        tasks.removeWhere((t) => t.id == task.id);
      });
    }
  }

  Future<void> _showAvatarSelector() async {
    final avatar = await showDialog<String>(
      context: context,
      builder: (context) => const AvatarSelector(),
    );

    if (avatar != null && mounted) {
      setState(() {
        selectedAvatar = avatar;
        widget.user.avatar = avatar;
      });

      await DatabaseHelper.instance.updateUser(widget.user);
    }
  }

  Future<void> _signOut() async {
    final result = await showDialog<bool>(
      context: context,
      builder:
          (context) => AlertDialog(
            title: const Text('Cerrar sesión'),
            content: const Text('¿Estás seguro que quieres cerrar sesión?'),
            actions: [
              TextButton(
                onPressed: () => Navigator.pop(context, false),
                child: const Text('Cancelar'),
              ),
              TextButton(
                onPressed: () => Navigator.pop(context, true),
                child: const Text('Cerrar sesión'),
              ),
            ],
          ),
    );

    if (result == true && mounted) {
      if (Platform.isAndroid || Platform.isIOS) {
        // En móvil cierra la app
        SystemNavigator.pop();
      } else {
        // En escritorio vuelve a LoginScreen
        Navigator.pushReplacement(
          context,
          MaterialPageRoute(builder: (context) => const LoginScreen()),
        );
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Dashboard"),
        actions: [
          IconButton(
            icon: const Icon(Icons.person),
            onPressed: _showAvatarSelector,
            tooltip: 'Seleccionar avatar',
          ),
          IconButton(
            icon: const Icon(Icons.add_task),
            onPressed: _addNewTask,
            tooltip: 'Agregar tarea',
          ),
          IconButton(
            icon: const Icon(Icons.logout),
            onPressed: _signOut,
            tooltip: 'Cerrar sesión',
          ),
        ],
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            PlayerInfoCard(user: widget.user, avatarPath: selectedAvatar),
            const SizedBox(height: 20),
            const Text(
              "Progreso general",
              style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16),
            ),
            const SizedBox(height: 8),
            SizedBox(
              height: 250,
              child: RadarChartWidget(tasks: tasks, categories: categories),
            ),
            const SizedBox(height: 20),
            const Text(
              "Habilidades",
              style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16),
            ),
            SkillProgressList(tasks: tasks, categories: categories),
            const SizedBox(height: 20),
            const Text(
              "Tareas",
              style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16),
            ),
            TaskList(
              tasks: tasks,
              categories: categories,
              onIncrementXP: _incrementTaskXP,
              onDelete: _deleteTask,
            ),
          ],
        ),
      ),
    );
  }
}
