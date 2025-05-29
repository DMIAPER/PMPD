/// lib/database/database_helper.dart
/// DatabaseHelper
/// -----------------------------------------------
/// Gestiona las operaciones CRUD con SQLite.
/// Maneja usuarios, tareas y categorías.
/// -----------------------------------------------
/// @author DMIAPER
/// @version 1.0.0
/// @date 27/05/2025
library;

import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart';
import '../models/user_model.dart';
import '../models/category_model.dart';
import '../models/task_model.dart';

class DatabaseHelper {
  static final DatabaseHelper instance = DatabaseHelper._init();
  static Database? _database;

  DatabaseHelper._init();

  Future<Database> get database async {
    if (_database != null) return _database!;
    _database = await _initDB('life_rpg.db');
    return _database!;
  }

  Future<Database> _initDB(String filePath) async {
    final dbPath = await getDatabasesPath();
    final path = join(dbPath, filePath);
    return await openDatabase(path, version: 1, onCreate: _createDB);
  }

  Future<void> _createDB(Database db, int version) async {
    await db.execute('''
      CREATE TABLE users(
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        name TEXT NOT NULL,
        password TEXT NOT NULL,
        avatar TEXT DEFAULT 'assets/images/default_avatar.png',
        totalXP INTEGER DEFAULT 0
      )
    ''');

    await db.execute('''
      CREATE TABLE categories(
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        name TEXT NOT NULL,
        color TEXT NOT NULL
      )
    ''');

    await db.execute('''
      CREATE TABLE tasks(
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        title TEXT NOT NULL,
        categoryId INTEGER NOT NULL,
        points INTEGER DEFAULT 0,
        FOREIGN KEY (categoryId) REFERENCES categories(id) ON DELETE CASCADE
      )
    ''');

    await db.insert('categories', {'name': 'Salud', 'color': '#ff4c4c'});
    await db.insert('categories', {
      'name': 'Productividad',
      'color': '#4c6bff',
    });
    await db.insert('categories', {'name': 'Creatividad', 'color': '#ffb74c'});
  }

  Future<int> registerUser(User user) async {
    final db = await database;
    return await db.insert('users', user.toMap());
  }

  Future<User?> loginUser(String name, String password) async {
    final db = await database;
    final maps = await db.query(
      'users',
      where: 'name = ? AND password = ?',
      whereArgs: [name, password],
    );
    if (maps.isNotEmpty) {
      return User.fromMap(maps.first);
    }
    return null;
  }

  Future<int> updateUser(User user) async {
    final db = await database;
    return await db.update(
      'users',
      user.toMap(),
      where: 'id = ?',
      whereArgs: [user.id],
    );
  }

  Future<int> insertCategory(Category category) async {
    final db = await database;
    return await db.insert('categories', category.toMap());
  }

  Future<List<Category>> getCategories() async {
    final db = await database;
    final maps = await db.query('categories');
    return maps.map((map) => Category.fromMap(map)).toList();
  }

  /// Inserta una tarea y retorna su ID
  Future<int> insertTask(Task task) async {
    final db = await database;
    try {
      int id = await db.insert('tasks', task.toMap());
      return id;
    } catch (e) {
      print("Error al insertar tarea: $e");
      return -1;
    }
  }

  Future<List<Task>> getTasks() async {
    final db = await database;
    final maps = await db.query('tasks');
    return maps.map((map) => Task.fromMap(map)).toList();
  }

  /// Actualiza una tarea existente, valida si tiene ID
  Future<int> updateTask(Task task) async {
    final db = await database;
    if (task.id == null) {
      throw Exception("No se puede actualizar una tarea sin ID.");
    }
    return await db.update(
      'tasks',
      task.toMap(),
      where: 'id = ?',
      whereArgs: [task.id],
    );
  }

  Future<int> deleteTask(int id) async {
    final db = await database;
    return await db.delete('tasks', where: 'id = ?', whereArgs: [id]);
  }

  /// Suma un punto de experiencia a una tarea
  Future<void> addPointToTask(int taskId) async {
    final db = await database;
    if (taskId == -1 || taskId == 0) {
      throw Exception("ID inválido para actualizar puntos de tarea.");
    }
    await db.rawUpdate(
      '''
      UPDATE tasks
      SET points = points + 1
      WHERE id = ?
    ''',
      [taskId],
    );
  }

  Future close() async {
    final db = await database;
    await db.close();
  }
}
