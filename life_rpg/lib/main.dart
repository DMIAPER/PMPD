/// lib/main.dart
/// MainApp
/// -----------------------------------------------
/// Punto de entrada de la aplicación Life RPG.
/// Configura tema, rutas y navegación principal.
/// -----------------------------------------------
/// @author DMIAPER
/// @version 1.0.0
/// @date 27/05/2025
library;

import 'dart:io'; // Para detectar si es escritorio
import 'package:flutter/material.dart';
import 'package:sqflite_common_ffi/sqflite_ffi.dart';
import 'screens/auth/login_register.dart';
import 'screens/dashboard/dashboard_screen.dart';
import 'models/user_model.dart'; // Import the User class

void main() {
  // Inicializar soporte SQLite en escritorio
  if (Platform.isWindows || Platform.isLinux || Platform.isMacOS) {
    sqfliteFfiInit();
    databaseFactory = databaseFactoryFfi;
  }

  runApp(const LifeRPGApp());
}

class LifeRPGApp extends StatelessWidget {
  const LifeRPGApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Life RPG',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        brightness: Brightness.dark,
        primarySwatch: Colors.deepPurple,
        fontFamily: 'MedievalSharp',
      ),
      initialRoute: '/',
      routes: {
        '/': (context) => const LoginScreen(),
        '/dashboard': (context) {
          final user = ModalRoute.of(context)?.settings.arguments as User?;
          return user != null
              ? DashboardScreen(user: user)
              : const LoginScreen();
        },
      },
    );
  }
}
