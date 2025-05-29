/// PlayerInfoCard
/// -----------------------------------------------
/// Tarjeta que muestra la información del usuario:
/// nombre, nivel, experiencia, barra de progreso y HP.
/// También muestra el avatar seleccionado.
/// -----------------------------------------------
/// - Utiliza el modelo `User` de user_model.dart.
/// - Se integra en la pantalla principal (Dashboard).
/// -----------------------------------------------
/// @author DMIAPER
/// @version 1.0.1
/// @date 27/05/2025
library;

import 'package:flutter/material.dart';
import 'package:life_rpg/models/user_model.dart';

class PlayerInfoCard extends StatelessWidget {
  final User user;
  final String avatarPath;

  const PlayerInfoCard({
    super.key,
    required this.user,
    required this.avatarPath,
  });

  @override
  Widget build(BuildContext context) {
    return Card(
      color: Colors.grey[850], // fondo oscuro
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
      child: Padding(
        padding: const EdgeInsets.all(16),
        child: Row(
          children: [
            /// Avatar circular del jugador con borde blanco para mejor contraste
            Container(
              decoration: BoxDecoration(
                color: Colors.deepPurpleAccent, // fondo morado
                shape: BoxShape.circle,
              ),
              padding: const EdgeInsets.all(3), // espacio para el borde blanco
              child: CircleAvatar(
                backgroundImage: AssetImage('assets/avatars/$avatarPath'),
                radius: 40,
                backgroundColor: Colors.white12,
              ),
            ),

            /// Información del usuario
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  /// Nombre del jugador en blanco, fuente más grande
                  Text(
                    user.name,
                    style: Theme.of(context).textTheme.headlineSmall?.copyWith(
                      color: Colors.white,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  const SizedBox(height: 6),

                  /// Nivel y experiencia en color blanco con opacidad para menor énfasis
                  Text(
                    "XP total: ${user.totalXP}",
                    style: TextStyle(color: Colors.white70),
                  ),
                  const SizedBox(height: 8),

                  /// Barra de progreso con color llamativo y fondo oscuro
                  LinearProgressIndicator(
                    value: (user.totalXP % 1000) / 1000,
                    color: Colors.deepPurpleAccent,
                    backgroundColor: Colors.deepPurple[900],
                    minHeight: 8,
                  ),
                  const SizedBox(height: 8),

                  /// HP en rojo brillante para visibilidad
                  const Text(
                    "HP: 100/100 ❤️",
                    style: TextStyle(
                      color: Colors.redAccent,
                      fontWeight: FontWeight.w600,
                    ),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
