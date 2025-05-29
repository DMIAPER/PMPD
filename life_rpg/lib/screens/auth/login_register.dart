/// LoginRegister
/// -----------------------------------------------
/// Vista que permite a los usuarios iniciar sesión o registrarse.
/// Utiliza el modelo de usuario actualizado y operaciones con SQLite.
/// -----------------------------------------------
/// Contiene validación de entrada, manejo de errores y navegación.
/// -----------------------------------------------
/// @author DMIAPER
/// @version 1.0.1
/// @date 27/05/2025
library;

import 'package:flutter/material.dart';
import '../../database/database_helper.dart';
import '../../models/user_model.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({super.key});

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  final TextEditingController _nameController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();
  bool isLogin = true;

  void _toggleMode() {
    setState(() => isLogin = !isLogin);
  }

  Future<void> _submit() async {
    final name = _nameController.text.trim();
    final password = _passwordController.text.trim();

    if (name.isEmpty || password.isEmpty) {
      _showDialog('Por favor, completa todos los campos.');
      return;
    }

    if (name.length < 3) {
      _showDialog('El nombre debe tener al menos 3 caracteres.');
      return;
    }

    if (password.length < 6) {
      _showDialog('La contraseña debe tener al menos 6 caracteres.');
      return;
    }

    if (isLogin) {
      try {
        final user = await DatabaseHelper.instance.loginUser(name, password);
        if (user != null && mounted) {
          Navigator.pushReplacementNamed(
            context,
            '/dashboard',
            arguments: user,
          );
        } else {
          _showDialog('Nombre o contraseña incorrectos.');
        }
      } catch (e) {
        _showDialog('Error al iniciar sesión: ${e.toString()}');
      }
    } else {
      try {
        final newUser = User(
          name: name,
          password: password,
          avatar: 'assets/images/default_avatar.png',
          totalXP: 0,
        );

        await DatabaseHelper.instance.registerUser(newUser);
        if (mounted) {
          _showDialog('¡Registro exitoso! Ahora puedes iniciar sesión.');
          setState(() {
            isLogin = true;
            _nameController.clear();
            _passwordController.clear();
          });
        }
      } catch (e) {
        _showDialog('Error al registrar: ${e.toString()}');
      }
    }
  }

  void _showDialog(String message) {
    showDialog(
      context: context,
      builder:
          (context) => AlertDialog(
            title: const Text('Info'),
            content: Text(message),
            actions: [
              TextButton(
                onPressed: () => Navigator.of(context).pop(),
                child: const Text('OK'),
              ),
            ],
          ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFF1D1F21),
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(32.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text(
                'Life RPG',
                style: Theme.of(context).textTheme.displaySmall?.copyWith(
                  color: Colors.amber,
                  fontFamily: 'MedievalSharp',
                ),
              ),
              const SizedBox(height: 40),
              TextField(
                controller: _nameController,
                decoration: const InputDecoration(
                  labelText: 'Nombre de usuario',
                  border: OutlineInputBorder(),
                ),
              ),
              const SizedBox(height: 20),
              TextField(
                controller: _passwordController,
                obscureText: true,
                decoration: const InputDecoration(
                  labelText: 'Contraseña',
                  border: OutlineInputBorder(),
                ),
              ),
              const SizedBox(height: 20),
              ElevatedButton(
                onPressed: _submit,
                child: Text(isLogin ? 'Iniciar Sesión' : 'Registrarse'),
              ),
              TextButton(
                onPressed: _toggleMode,
                child: Text(
                  isLogin
                      ? '¿No tienes cuenta? Regístrate'
                      : '¿Ya tienes cuenta? Inicia sesión',
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
