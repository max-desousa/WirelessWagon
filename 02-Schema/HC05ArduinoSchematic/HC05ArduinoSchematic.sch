EESchema Schematic File Version 4
EELAYER 30 0
EELAYER END
$Descr A4 11693 8268
encoding utf-8
Sheet 1 1
Title ""
Date ""
Rev ""
Comp ""
Comment1 ""
Comment2 ""
Comment3 ""
Comment4 ""
$EndDescr
$Comp
L MCU_Module:Arduino_Nano_v3.x A1
U 1 1 60234D0A
P 6950 2550
F 0 "A1" H 6950 1461 50  0000 C CNN
F 1 "Arduino_Nano_v3.x" H 6950 1370 50  0000 C CNN
F 2 "Module:Arduino_Nano" H 6950 2550 50  0001 C CIN
F 3 "http://www.mouser.com/pdfdocs/Gravitech_Arduino_Nano3_0.pdf" H 6950 2550 50  0001 C CNN
	1    6950 2550
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR0101
U 1 1 602394AE
P 1200 2150
F 0 "#PWR0101" H 1200 1900 50  0001 C CNN
F 1 "GND" H 1205 1977 50  0000 C CNN
F 2 "" H 1200 2150 50  0001 C CNN
F 3 "" H 1200 2150 50  0001 C CNN
	1    1200 2150
	1    0    0    -1  
$EndComp
$Comp
L power:PWR_FLAG #FLG0101
U 1 1 6023B590
P 1500 1450
F 0 "#FLG0101" H 1500 1525 50  0001 C CNN
F 1 "PWR_FLAG" V 1500 1800 50  0000 C CNN
F 2 "" H 1500 1450 50  0001 C CNN
F 3 "~" H 1500 1450 50  0001 C CNN
	1    1500 1450
	0    1    1    0   
$EndComp
$Comp
L power:PWR_FLAG #FLG0102
U 1 1 6023BFF0
P 1500 1950
F 0 "#FLG0102" H 1500 2025 50  0001 C CNN
F 1 "PWR_FLAG" V 1500 2078 50  0000 L CNN
F 2 "" H 1500 1950 50  0001 C CNN
F 3 "~" H 1500 1950 50  0001 C CNN
	1    1500 1950
	0    1    1    0   
$EndComp
$Comp
L EmbeddedModules:HC05 BT1
U 1 1 60240106
P 3100 3000
F 0 "BT1" H 3183 3475 50  0000 C CNN
F 1 "HC05" H 3183 3384 50  0000 C CNN
F 2 "" H 3150 3050 50  0001 C CNN
F 3 "" H 3150 3050 50  0001 C CNN
	1    3100 3000
	1    0    0    -1  
$EndComp
$Comp
L power:+3.3V #PWR0102
U 1 1 60241BDA
P 1200 1350
F 0 "#PWR0102" H 1200 1200 50  0001 C CNN
F 1 "+3.3V" H 1215 1523 50  0000 C CNN
F 2 "" H 1200 1350 50  0001 C CNN
F 3 "" H 1200 1350 50  0001 C CNN
	1    1200 1350
	1    0    0    -1  
$EndComp
$Comp
L power:+5V #PWR0103
U 1 1 60241402
P 1200 750
F 0 "#PWR0103" H 1200 600 50  0001 C CNN
F 1 "+5V" H 1215 923 50  0000 C CNN
F 2 "" H 1200 750 50  0001 C CNN
F 3 "" H 1200 750 50  0001 C CNN
	1    1200 750 
	1    0    0    -1  
$EndComp
$Comp
L power:PWR_FLAG #FLG0103
U 1 1 6023A456
P 1500 850
F 0 "#FLG0103" H 1500 925 50  0001 C CNN
F 1 "PWR_FLAG" V 1500 978 50  0000 L CNN
F 2 "" H 1500 850 50  0001 C CNN
F 3 "~" H 1500 850 50  0001 C CNN
	1    1500 850 
	0    1    1    0   
$EndComp
Wire Wire Line
	1200 1350 1200 1450
Wire Wire Line
	1200 1450 1500 1450
Wire Wire Line
	1200 750  1200 850 
Wire Wire Line
	1200 850  1500 850 
Wire Wire Line
	1200 2150 1200 1950
Wire Wire Line
	1200 1950 1500 1950
Text GLabel 1400 1800 2    50   Input ~ 0
GND
Wire Wire Line
	1200 1950 1200 1800
Wire Wire Line
	1200 1800 1400 1800
Connection ~ 1200 1950
Text GLabel 1350 1600 2    50   Input ~ 0
+3.3V
Wire Wire Line
	1200 1450 1200 1600
Wire Wire Line
	1200 1600 1350 1600
Connection ~ 1200 1450
Text GLabel 1400 1000 2    50   Input ~ 0
+5V
Wire Wire Line
	1200 850  1200 1000
Wire Wire Line
	1200 1000 1400 1000
Connection ~ 1200 850 
Text GLabel 6850 1250 1    50   Input ~ 0
+5V
Wire Wire Line
	6850 1250 6850 1550
$Comp
L Transistor_BJT:PN2222A Q1
U 1 1 6025A5A0
P 3850 4350
F 0 "Q1" H 4040 4396 50  0000 L CNN
F 1 "PN2222A" H 4040 4305 50  0000 L CNN
F 2 "Package_TO_SOT_THT:TO-92_Inline" H 4050 4275 50  0001 L CIN
F 3 "http://www.fairchildsemi.com/ds/PN/PN2222A.pdf" H 3850 4350 50  0001 L CNN
	1    3850 4350
	1    0    0    -1  
$EndComp
$Comp
L Device:R R2
U 1 1 6025CD69
P 3350 4350
F 0 "R2" V 3143 4350 50  0000 C CNN
F 1 "R" V 3234 4350 50  0000 C CNN
F 2 "" V 3280 4350 50  0001 C CNN
F 3 "~" H 3350 4350 50  0001 C CNN
	1    3350 4350
	0    1    1    0   
$EndComp
$Comp
L Device:R R1
U 1 1 6025D099
P 3000 4850
F 0 "R1" H 2930 4804 50  0000 R CNN
F 1 "10K" H 2930 4895 50  0000 R CNN
F 2 "" V 2930 4850 50  0001 C CNN
F 3 "~" H 3000 4850 50  0001 C CNN
	1    3000 4850
	-1   0    0    1   
$EndComp
Wire Wire Line
	3000 4700 3000 4350
Wire Wire Line
	3000 4350 3200 4350
Wire Wire Line
	3500 4350 3650 4350
Text GLabel 3000 5200 3    50   Input ~ 0
GND
Wire Wire Line
	3000 5200 3000 5000
$Comp
L EmbeddedModules:BidirectionalLevelShifter B1
U 1 1 602CB046
P 4950 3000
F 0 "B1" H 4925 3475 50  0000 C CNN
F 1 "BidirectionalLevelShifter" H 4925 3384 50  0000 C CNN
F 2 "" H 5550 3400 50  0001 C CNN
F 3 "" H 5550 3400 50  0001 C CNN
	1    4950 3000
	1    0    0    -1  
$EndComp
$Comp
L Switch:SW_Push SW1
U 1 1 602D0A0C
P 5700 2150
F 0 "SW1" H 5700 2435 50  0000 C CNN
F 1 "SW_Push" H 5700 2344 50  0000 C CNN
F 2 "" H 5700 2350 50  0001 C CNN
F 3 "~" H 5700 2350 50  0001 C CNN
	1    5700 2150
	1    0    0    -1  
$EndComp
Wire Wire Line
	5900 2150 6450 2150
Text GLabel 5300 2150 0    50   Input ~ 0
GND
Wire Wire Line
	5300 2150 5500 2150
Text GLabel 5350 3050 2    50   Input ~ 0
GND
Text GLabel 4500 3050 0    50   Input ~ 0
GND
Wire Wire Line
	4500 3050 4600 3050
Wire Wire Line
	5250 3050 5350 3050
Wire Wire Line
	5250 2950 5350 2950
Text GLabel 4500 2950 0    50   Input ~ 0
+3.3V
Wire Wire Line
	4500 2950 4600 2950
Text GLabel 5350 2950 2    50   Input ~ 0
+5V
Wire Wire Line
	5250 3250 6450 3250
Wire Wire Line
	6450 3150 5250 3150
Wire Wire Line
	6450 3050 5700 3050
Wire Wire Line
	5700 3050 5700 2850
Wire Wire Line
	5700 2850 5250 2850
Wire Wire Line
	5250 2750 5800 2750
Wire Wire Line
	5800 2750 5800 2950
Wire Wire Line
	5800 2950 6450 2950
Wire Wire Line
	3400 2750 4600 2750
Wire Wire Line
	3400 2850 4600 2850
Wire Wire Line
	3400 3250 4600 3250
Wire Wire Line
	4600 3150 4100 3150
Wire Wire Line
	4100 3150 4100 2950
Wire Wire Line
	4100 2950 3400 2950
Wire Wire Line
	3950 3050 3950 4150
Text GLabel 3950 4850 3    50   Input ~ 0
GND
Wire Wire Line
	3950 4850 3950 4550
Text GLabel 3500 3150 2    50   Input ~ 0
+3.3V
Wire Wire Line
	3500 3150 3400 3150
Wire Wire Line
	3400 3050 3950 3050
Text GLabel 6100 2450 0    50   Output ~ 0
BluetoothPower
Wire Wire Line
	6100 2450 6450 2450
Text GLabel 2750 4350 0    50   Input ~ 0
BluetoothPower
Wire Wire Line
	2750 4350 3000 4350
Connection ~ 3000 4350
$EndSCHEMATC
