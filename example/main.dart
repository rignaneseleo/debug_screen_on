import 'package:flutter/material.dart';
import 'package:debug_screen_on/debug_screen_on.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Debug Screen On Example'),
        ),
        body: Center(
          child: Text(
            'Screen automatically stays on during debugging when:\n'
            '• Debugger is attached\n'
            '• ADB is enabled\n\n'
            'No setup required - just add the dependency!',
            textAlign: TextAlign.center,
          ),
        ),
      ),
    );
  }
}