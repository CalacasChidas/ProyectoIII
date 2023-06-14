const int pin_led1 = 2;
const int pin_led2 = 3;
const int pin_led3 = 4;
const int pin_led4 = 5;
const int pin_led5 = 6;
const int pin_boton1 = 7;
const int pin_boton2 = 9;
const int pin_boton3 = 10;
const int pin_boton4 = 11;
const int pin_boton5 = 12;
const int BUZZER = 8;
const int led_delay = 100;
const int led_on_duration = 2000;

int led = 1;
int direction = 1;
bool shoot = false;
bool buttonPressed = false;

void setup() {
  pinMode(pin_led1, OUTPUT);
  pinMode(pin_led2, OUTPUT);
  pinMode(pin_led3, OUTPUT);
  pinMode(pin_led4, OUTPUT);
  pinMode(pin_led5, OUTPUT);
  pinMode(pin_boton1, INPUT_PULLUP);
  pinMode(BUZZER, OUTPUT);
  Serial.begin(19200);
}

void loop() {
  lights();
  checkButton();
/*
  int estado_boton2;
  estado_boton2 = digitalRead(pin_boton2);
  if(estado_boton2 == HIGH){
      Serial.println("U");
      delay(500);
  }
  int estado_boton3;
  estado_boton3 = digitalRead(pin_boton3);
  if(estado_boton3 == HIGH){
      Serial.println("D");
      delay(500);
  }
  int estado_boton4;
  estado_boton4 = digitalRead(pin_boton4);
  if(estado_boton4 == HIGH){
      Serial.println("L");
      delay(500);
  }
  int estado_boton5;
  estado_boton5 = digitalRead(pin_boton5);
  if(estado_boton5 == HIGH){
      Serial.println("R");
      delay(500);
  }*/
}

void lights() {
  digitalWrite(pin_led1, led == 1);
  digitalWrite(pin_led2, led == 2);
  digitalWrite(pin_led3, led == 3);
  digitalWrite(pin_led4, led == 4);
  digitalWrite(pin_led5, led == 5);

  delay(led_delay);

  if (!shoot) {
    led += direction;

    if (led > 5) {
      led = 4;
      direction = -1;
    } else if (led < 1) {
      led = 2;
      direction = 1;
    }
  }
}

void checkButton() {
  int estado_boton1 = digitalRead(pin_boton1);

  if (estado_boton1 == LOW && !buttonPressed) {
    shoot = true;
    buttonPressed = true;
    delay(led_delay);
  }

  if (estado_boton1 == HIGH) {
    buttonPressed = false;
  }

  if (shoot) {
    digitalWrite(led, HIGH);
    if (led == 4) {
      delay(50);
      Serial.println("X");
      delay(2000);
      tone(BUZZER, 1000, 100);
      delay(200);
      tone(BUZZER, 1000, 100);
      delay(200);
      tone(BUZZER, 1000, 100);
    }
    delay(led_on_duration);
    digitalWrite(led, LOW);
    shoot = false;
  }
}