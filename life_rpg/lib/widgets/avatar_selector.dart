/// Avatar Selector
/// -----------------------------------------------
/// Alert dialog que permitirá seleccionar un avatar de una lista de imágenes.
/// Muestra un diálogo con una cuadrícula de avatares disponibles.
/// -----------------------------------------------
/// @author DMIAPER (Diógenes Miaja Pérez)
/// @version 1.0.1
/// @date 27/05/2025
library;

import 'package:flutter/material.dart';

class AvatarSelector extends StatelessWidget {
  const AvatarSelector({super.key});

  final List<String> avatars = const [
    'avatar1.png',
    'avatar2.png',
    'avatar3.png',
    'avatar4.png',
  ];

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      title: const Text("Selecciona tu avatar"),
      content: SizedBox(
        width: double.maxFinite,
        child: GridView.builder(
          shrinkWrap: true,
          itemCount: avatars.length,
          gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
            crossAxisCount: 3, // Más columnas para escritorio
            crossAxisSpacing: 10,
            mainAxisSpacing: 10,
            childAspectRatio: 1, // Más cuadrado y compacto
          ),
          itemBuilder: (context, index) {
            final avatar = avatars[index];
            return GestureDetector(
              onTap: () => Navigator.of(context).pop(avatar),
              child: CircleAvatar(
                radius: 28, // Tamaño reducido
                backgroundImage: AssetImage('assets/avatars/$avatar'),
              ),
            );
          },
        ),
      ),
      actions: [
        TextButton(
          onPressed: () => Navigator.of(context).pop(),
          child: const Text("Cancelar"),
        ),
      ],
    );
  }
}
