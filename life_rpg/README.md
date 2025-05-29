# 🛡️ Life RPG

Life RPG es una aplicación de productividad gamificada que convierte tus tareas diarias en misiones para subir de nivel en diferentes áreas de tu vida. Administra tus actividades, gana experiencia (XP), desbloquea logros y personaliza tu avatar. ¡Conviértete en la mejor versión de ti mismo!

## 📱 Características Principales

- 🎮 **Sistema de experiencia (XP):**
  - Gana puntos al completar tareas.
  - Progreso visual con barras y niveles por categoría.

- 🧍 **Avatares personalizables:**
  - Selecciona tu personaje inicial.
  - Cambia el avatar desde el dashboard.

- 🗂️ **Gestión de tareas y categorías:**
  - Crea tareas con una categoría asignada.
  - Visualiza tu progreso por área (Salud, Productividad, Creatividad, etc.).

- 🏆 **Sistema de logros (WIP):**
  - Insignias y recompensas por rachas y logros únicos.

- 📊 **Visualización del progreso:**
  - Gráficos radar por categoría.
  - Barras de habilidad.

## 🧱 Estructura de la App

- `LoginScreen`: Inicio de sesión y registro.
- `DashboardScreen`: Pantalla principal del usuario.
- `TaskList`, `SkillProgressList`, `RadarChartWidget`: Widgets personalizados para la visualización de tareas y progreso.
- `DatabaseHelper`: Persistencia local con SQLite.

## 🚀 Instalación

### 1. Clona el repositorio

```bash
git clone https://github.com/tu-usuario/life-rpg.git
cd life-rpg
````

### 2. Instala dependencias

```bash
flutter pub get
```

### 3. Corre la aplicación

```bash
flutter run
```

> Asegúrate de tener un emulador o dispositivo conectado.

## 📦 Generar Builds

### Android APK

```bash
flutter build apk --release
```

El archivo se generará en: `build/app/outputs/flutter-apk/app-release.apk`

### Windows ejecutable

```bash
flutter build windows
```

El ejecutable estará en: `build/windows/runner/Release/`

### Flutter Doctor

Para verificar si tu entorno está correctamente configurado:

```bash
flutter doctor
```

## 🖼️ Capturas de pantalla (opcional)

![Vista de Login](https://github.com/user-attachments/assets/92d85643-2a7e-4b1a-af71-8c0b98a6a442)

![Vista de Dashboard](https://github.com/user-attachments/assets/2ad3622c-604b-4b7a-ba1f-bf6e0d57bc97)


## 📚 Dependencias

* `sqflite`: SQLite para persistencia local
* `path_provider`: Manejo de rutas del sistema
* `fl_chart`: Radar y gráficos visuales
* `google_fonts`: Tipografías personalizadas
* `flutter_launcher_icons`: Iconos de app (si se implementa)

## 📌 Próximas Funcionalidades

* Sistema completo de logros
* Rachas de productividad
* Notificaciones diarias
* Backups en la nube

## 👨‍💻 Autor

* **DMIAPER** – https://github.com/DMIAPER

---

**¡Gracias por usar Life RPG!** Si te gusta el proyecto, dale ⭐ y contribuye con ideas o mejoras.

```
