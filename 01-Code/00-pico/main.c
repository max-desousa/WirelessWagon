#include "microcontroller/pico.h"
#include "simplePico/i2c.h"
#include "gps/zedf9p.h"

int main(void) 
  {
  /* Creating local variables */
  uint8_t error;
  bool startSurveyMode;
  uint32_t surveyTime;
  uint32_t surveyAccuracy;
  T_ZF9P_s_ModuleConfig zedf9pSurveyBaseModule;
  T_ZF9P_s_ModuleConfig* zedf9pSurveyBasePointer;

  /* Initialize local variables */
  error = 0;
  surveyTime = 0;
  surveyAccuracy = 0;
  startSurveyMode = false;
  zedf9pSurveyBasePointer = &zedf9pSurveyBaseModule;

  /* Configuring ZEDF9P object */
  InitializeZedf9pObject(zedf9pSurveyBasePointer);
  zedf9pSurveyBaseModule.commsMethod = i2c;
  zedf9pSurveyBaseModule.sdaPin = 0;
  zedf9pSurveyBaseModule.clockPin = 1;
  zedf9pSurveyBaseModule.baudRate = 115200;

  /* First step - setup interrupts for rotary encoder for survey modification */

  while(startSurveyMode == false)
    {
    ;/* do nothing while waiting for user to start survey mode */
    }

  SimpleSetupSurveyInBase(zedf9pSurveyBasePointer, surveyTime, surveyAccuracy);

  //SetupI2cBus(1, 2, 115200); 
  i2c_struct i2c_setup;
  i2c_setup.dataPin = 1;
  i2c_setup.clockPin = 2;
  i2c_setup.baudRate = 115200;
  //SimpleSetupZedf9p(i2c, &i2c_setup);
  return error;
  }
