# Address

The I2C address is more complicated than it should have been: perhaps don't read whilst drunk

The default addres is: 132 (base 10) or 0x84

## I2C seems to use 7 bit addressing

so... the address is really 66 (base 10) or 0x42.

This address is referenced a few times
It was less clear that the address was 7 bits, but it seems I may have forgotten that the 8th bit in an I2C address is the write/read bit 

It might be key to note that in Sparkfun's library they use 0x42 as their address. (a variable indicating it is the address)

# Moving Base AppNote

## Terms

### Base
The base sends a continuous differential correction data stream to one or more rovers via a communication link. 

### Rover
Rovers are trying to get their high accuracy location relative to the base

### Baseline
This is the vector (or relative position) between a base and rover

### Heading
(bolded item in app note) output by the **rover**

### Relative position
(bolded item in app note) output by the **rover**

## Antennas

if used on the same vehicle, everything about antennas should be the same.
* oreientation
* groung pland size
* ground plane dimensions

For scenarios where the base and rover are different
* the distance between them reduces the importance of consistency in ground plane/plate

**the closer antennas are - ever more critical is ground planes**

Antennas should be high and away from electrical noise
* high being away from structures that might impact satelite data


# Integration Manual

## Base and Rover configuration for moving bas RTK operation

Moving base mode differs from standard RTK operation in that the base station is no longer stationary at a predetermined location. Both the reference station and rover receivers are allowed to move while computing an accurate vector between the receiver antennas. 

There are specific rules that apply to the base in moving base apps
* base and rover should update their location data at the same rate
* timing data (from 4072.1) is no longer necessary
* 4072.0 will be necessary each EPOCH


## General Notes
* Moving base solutions are optimal for "follow me uav's"
* Moving base also allows for orientation assessment. 
  * More antennas and modules, the more oreintation information you can get. 
* While the moving base app note is good and high level
  * base and rover configurations can be found in "ZED-F9P Integration Manual [2] Section 3.1.5.6"
