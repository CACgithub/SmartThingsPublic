metadata {
    // Automatically generated. Make future change here.
    definition (name: "CT101 Thermostat (Cumtomized)", namespace: "smartthings", author: "Customized") {
        capability "Actuator"
        capability "Temperature Measurement"
        capability "Relative Humidity Measurement"
        capability "Thermostat"
        capability "Battery"
        capability "Configuration"
        capability "Refresh"
        capability "Sensor"
    capability "Polling"     
        command "heatLevelUp"
    command "heatLevelDown"
    command "coolLevelUp"
    command "coolLevelDown"        
        attribute "thermostatFanState", "string"
    attribute "lastUpdate", "string"       
        command "switchMode"
        command "switchFanMode"
        command "quickSetCool"
        command "quickSetHeat"
    command "setTemperature"

        fingerprint deviceId: "0x08", inClusters: "0x20, 0x81, 0x87, 0x72, 0x31, 0x40, 0x42, 0x44, 0x45, 0x43, 0x86, 0x70, 0x80, 0x85, 0x60", manufacturer: "2gig",  model: "CT100"
    }

preferences {
 input("tzOffset", "number", title: "Time zone offset +/-x?", required: false, range: "-12..14", defaultValue: -8, description: "Time Zone Offset ie -8.")  
  }

    // removed simulator metadata

    tiles (scale: 2) {

        multiAttributeTile(name:"temperature", type:"thermostat", width:6, height:4, canChangeBackground: false) {
              tileAttribute("device.temperature", key: "PRIMARY_CONTROL") {
                    attributeState("default", label:'${currentValue}°', unit:"F"
                )
              }

              tileAttribute("device.humidity", key: "SECONDARY_CONTROL") {
                    attributeState("default", label:'${currentValue}%', unit:"%")
              }
              tileAttribute("device.thermostatOperatingState", key: "OPERATING_STATE") {
                    attributeState("idle", backgroundColor:"#44b621")
                    attributeState("heating", backgroundColor:"#ffa81e")
                    attributeState("cooling", backgroundColor:"#269bd2")
              }
              tileAttribute("device.thermostatMode", key: "THERMOSTAT_MODE") {
                    attributeState("off", label:'${name}')
                    attributeState("heat", label:'${name}')
                    attributeState("cool", label:'${name}')
                    attributeState("auto", label:'${name}')
              }
              tileAttribute("device.heatingSetpoint", key: "HEATING_SETPOINT") {
                    attributeState("default", label:'${currentValue}')
              }
              tileAttribute("device.coolingSetpoint", key: "COOLING_SETPOINT") {
                    attributeState("default", label:'${currentValue}')
              }                    
        }

        valueTile("temperature1", "device.temperature", width: 1, height: 1, icon:"st.Weather.weather2") {
            state("temperature", label:'${currentValue}°', unit:"F",
                backgroundColors:[
                    [value: 55, color: "#153591"],
                    [value: 60, color: "#1e9cbb"],
                    [value: 63, color: "#90d2a7"],
                    [value: 65, color: "#44b621"],
                    [value: 75, color: "#f1d801"],
                    [value: 80, color: "#d04e00"],
                    [value: 90, color: "#bc2323"]
                ]
            )
        }

        standardTile("mode", "device.thermostatMode", inactiveLabel: false,  height: 2, width: 2, decoration: "flat") {
            state "off", label:':', action:"switchMode", icon: "st.thermostat.heating-cooling-off" ,nextState:"to_heat"
            state "heat", label:'${name}', action:"switchMode", icon: "st.Weather.weather14", backgroundColor: '#E14902', nextState:"to_cool"
            state "cool", label:'${name}', action:"switchMode", icon: "st.Weather.weather7", backgroundColor: '#1e9cbb', nextState:"..."
            state "auto", label:'${name}', action:"switchMode", icon: "st.Weather.weather3", backgroundColor: '#44b621', nextState:"..."
            state "emergency heat", label:'${name}', action:"switchMode", nextState:"..."
            state "to_heat", label: ":", action:"switchMode", nextState:"to_cool"
            state "to_cool", label: "cool", action:"switchMode", nextState:"..."
            state "...", label: "...", action:"off", nextState:"off"
        }
             
        standardTile("fanMode", "device.thermostatFanMode", inactiveLabel: false,  height: 2, width: 2, decoration: "flat") {
            state "fanAuto", label:'${name}', action:"switchFanMode", icon: "st.Appliances.appliances11", backgroundColor: '#44b621'
            state "fanOn", label:'${name}', action:"switchFanMode", icon: "st.Appliances.appliances11", backgroundColor: '#44b621'
            state "fanCirculate", label:'${name}', action:"switchFanMode",  icon: "st.Appliances.appliances11", backgroundColor: '#44b621'
        }
   valueTile("coolingSetpoint", "device.coolingSetpoint", inactiveLabel: false,  height: 2, width: 2) 
          {
          state "default", label:'Cool\n${currentValue}°', unit:"F", backgroundColor:"#87CEFA"
         }

     valueTile("heatingSetpoint", "device.heatingSetpoint", inactiveLabel: false,  height: 2, width: 2) 
        {
      state "default", label:'Heat\n${currentValue}°', unit:"F", backgroundColor:"#FF0000"
   
    }
    
         standardTile("heatLevelUp", "device.heatingSetpoint", canChangeIcon: false, inactiveLabel: false,  height: 1, width: 1) {
                        state "heatLevelUp", label:'  ', action:"heatLevelUp", icon:"st.thermostat.thermostat-up"
        }
        standardTile("heatLevelDown", "device.heatingSetpoint", canChangeIcon: false, inactiveLabel: false,  height: 1, width: 1) {
                        state "heatLevelDown", label:'  ', action:"heatLevelDown", icon:"st.thermostat.thermostat-down"
        }
        standardTile("coolLevelUp", "device.heatingSetpoint", canChangeIcon: false, inactiveLabel: false,  height: 1, width: 1) {
                        state "coolLevelUp", label:'  ', action:"coolLevelUp", icon:"st.thermostat.thermostat-up"
        }
        standardTile("coolLevelDown", "device.heatingSetpoint", canChangeIcon: false, inactiveLabel: false,  height: 1, width: 1) {
                        state "coolLevelDown", label:'  ', action:"coolLevelDown", icon:"st.thermostat.thermostat-down"
        }
        
        
        valueTile("humidity", "device.humidity", inactiveLabel: false,  height: 1, width: 1) {
            state "humidity", label:'${currentValue}%rh', unit:"",
               backgroundColors : [
                    [value: 01, color: "#724529"],
                    [value: 11, color: "#724529"],
                    [value: 21, color: "#724529"],
                    [value: 35, color: "#44b621"],
                    [value: 49, color: "#44b621"],
                    [value: 50, color: "#1e9cbb"]
         ]        
        }
        
        valueTile("battery", "device.battery", inactiveLabel: false,  height: 1, width: 1) {
            state "battery", label:'${currentValue}%', unit:"",
             backgroundColors : [
                  [value: 20, color: "#720000"],
                  [value: 40, color: "#724529"],
                  [value: 60, color: "#00cccc"],
                  [value: 80, color: "#00b621"],
                  [value: 90, color: "#009c00"],
                  [value: 100, color: "#44b621"]
           ]
        }
        
        valueTile("thermostatOperatingState", "device.thermostatOperatingState", inactiveLabel: false,  height: 1, width: 1, decoration: "flat") {
            state "thermostatOperatingState", label:'${currentValue}', unit:""
        }
        
        standardTile("refresh", "device.thermostatMode", inactiveLabel: false,  height: 2, width: 2, decoration: "flat") {
            state "default", action:"refresh.refresh", icon:"st.secondary.refresh"
        }
        
        valueTile("status", "device.lastUpdate", width: 2, height: 1, decoration: "flat") {
            state "default", label: 'Last Update: ${currentValue}'

}

        main "temperature1"
        details(["temperature",   
        "heatLevelUp", "heatingSetpoint" , "coolingSetpoint",  "coolLevelUp", "heatLevelDown", "coolLevelDown",
        "mode", "refresh", "fanMode",
        "status",, "temperature1", "humidity", "battery","thermostatOperatingState"])
        
    }
}

def parse(String description)
{
    def result = []
    if (description == "updated") {
    } else {
        def zwcmd = zwave.parse(description, [0x42:2, 0x43:2, 0x31: 2, 0x60: 3])
        if (zwcmd) {
            result += zwaveEvent(zwcmd)
        } else {
            log.debug "$device.displayName couldn't parse $description"
        }
    }
    if (!result) {
        return null
    }
    if (result.size() == 1 && (!state.lastbatt || now() - state.lastbatt > 48*60*60*1000)) {
        result << response(zwave.batteryV1.batteryGet().format())
    }
    log.debug "$device.displayName parsed '$description' to $result"
    result
}

def zwaveEvent(physicalgraph.zwave.commands.multichannelv3.MultiChannelCmdEncap cmd) {
    def result = null
    def encapsulatedCommand = cmd.encapsulatedCommand([0x42:2, 0x43:2, 0x31: 2])
    log.debug ("Command from endpoint ${cmd.sourceEndPoint}: ${encapsulatedCommand}")
    if (encapsulatedCommand) {
        result = zwaveEvent(encapsulatedCommand)
        if (cmd.sourceEndPoint == 1) {    // indicates a response to refresh() vs an unrequested update
            def event = ([] + result)[0]  // in case zwaveEvent returns a list
            def resp = nextRefreshQuery(event?.name)
            if (resp) {
                log.debug("sending next refresh query: $resp")
                result = [] + result + response(["delay 200", resp])
            }
        }
    }
    log.debug "Parse returned $result"
    result
}

def zwaveEvent(physicalgraph.zwave.commands.thermostatsetpointv2.ThermostatSetpointReport cmd)
{
    def cmdScale = cmd.scale == 1 ? "F" : "C"
    def temp = convertTemperatureIfNeeded(cmd.scaledValue, cmdScale, cmd.precision)
    def unit = getTemperatureScale()
    def map1 = [ value: temp, unit: unit, displayed: false ]
    switch (cmd.setpointType) {
        case 1:
            map1.name = "heatingSetpoint"
            break;
        case 2:
            map1.name = "coolingSetpoint"
            break;
        default:
            log.debug "unknown setpointType $cmd.setpointType"
            return
    }

    // So we can respond with same format
    state.size = cmd.size
    state.scale = cmd.scale
    state.precision = cmd.precision

    def mode = device.latestValue("thermostatMode")
    if (mode && map1.name.startsWith(mode) || (mode == "emergency heat" && map1.name == "heatingSetpoint")) {
        def map2 = [ name: "thermostatSetpoint", value: temp, unit: unit ]
        [ createEvent(map1), createEvent(map2) ]
      
    } else {
        createEvent(map1)
    
    }
}

def zwaveEvent(physicalgraph.zwave.commands.sensormultilevelv2.SensorMultilevelReport cmd)
{
log.debug "in sensor multilevel v2 cmd type = $cmd.sensorType";
    def map = [:]
    map.displayed = true 
    map.isStateChange = true 
    if (cmd.sensorType == 1) {
        map.name = "temperature"
        map.unit = getTemperatureScale()
        map.value = convertTemperatureIfNeeded(cmd.scaledSensorValue, cmd.scale == 1 ? "F" : "C", cmd.precision)
        
        if (settings.tzOffset == null)
            settings.tzOffset = -5
 
        def now = new Date()
        def tf = new java.text.SimpleDateFormat("\n h:mm a\n MM/dd/yyyy")
        tf.setTimeZone(TimeZone.getTimeZone("GMT${settings.tzOffset}"))
        def newtime = "${tf.format(now)}" as String   
        sendEvent(name: "lastUpdate", value: newtime, descriptionText: "Last Update: $newtime")
    } else if (cmd.sensorType == 5) {
        map.name = "humidity"
        map.unit = "%"
        map.value = cmd.scaledSensorValue
    }
    map
    createEvent(map)
}

def zwaveEvent(physicalgraph.zwave.commands.thermostatoperatingstatev2.ThermostatOperatingStateReport cmd)
{

    def map = [name: "thermostatOperatingState" ]
    switch (cmd.operatingState) {
        case physicalgraph.zwave.commands.thermostatoperatingstatev2.ThermostatOperatingStateReport.OPERATING_STATE_IDLE:
            map.value = "idle"
            break
        case physicalgraph.zwave.commands.thermostatoperatingstatev2.ThermostatOperatingStateReport.OPERATING_STATE_HEATING:
            map.value = "heating"
            break
        case physicalgraph.zwave.commands.thermostatoperatingstatev2.ThermostatOperatingStateReport.OPERATING_STATE_COOLING:
            map.value = "cooling"
            break
        case physicalgraph.zwave.commands.thermostatoperatingstatev2.ThermostatOperatingStateReport.OPERATING_STATE_FAN_ONLY:
            map.value = "fan only"
            break
        case physicalgraph.zwave.commands.thermostatoperatingstatev2.ThermostatOperatingStateReport.OPERATING_STATE_PENDING_HEAT:
            map.value = "pending heat"
            break
        case physicalgraph.zwave.commands.thermostatoperatingstatev2.ThermostatOperatingStateReport.OPERATING_STATE_PENDING_COOL:
            map.value = "pending cool"
            break
        case physicalgraph.zwave.commands.thermostatoperatingstatev2.ThermostatOperatingStateReport.OPERATING_STATE_VENT_ECONOMIZER:
            map.value = "vent economizer"
            break
    }
    
    def result = createEvent(map)
    if (result.isStateChange && device.latestValue("thermostatMode") == "auto" && (result.value == "heating" || result.value == "cooling")) {
        def thermostatSetpoint = device.latestValue("${result.value}Setpoint")
        result = [result, createEvent(name: "thermostatSetpoint", value: thermostatSetpoint, unit: getTemperatureScale())]
    }
    result
}

def zwaveEvent(physicalgraph.zwave.commands.thermostatfanstatev1.ThermostatFanStateReport cmd) {
    def map = [name: "thermostatFanState", unit: ""]
    switch (cmd.fanOperatingState) {
        case 0:
            map.value = "idle"
            break
        case 1:
            map.value = "running"
            break
        case 2:
            map.value = "running high"
            break
    }
    createEvent(map)
}

def zwaveEvent(physicalgraph.zwave.commands.thermostatmodev2.ThermostatModeReport cmd) {
    def map = [name: "thermostatMode"]
    def thermostatSetpoint = null
    switch (cmd.mode) {
        case physicalgraph.zwave.commands.thermostatmodev2.ThermostatModeReport.MODE_OFF:
            map.value = "off"
            break
        case physicalgraph.zwave.commands.thermostatmodev2.ThermostatModeReport.MODE_HEAT:
            map.value = "heat"
            thermostatSetpoint = device.latestValue("heatingSetpoint")
            break
        case physicalgraph.zwave.commands.thermostatmodev2.ThermostatModeReport.MODE_AUXILIARY_HEAT:
            map.value = "emergency heat"
            thermostatSetpoint = device.latestValue("heatingSetpoint")
            break
        case physicalgraph.zwave.commands.thermostatmodev2.ThermostatModeReport.MODE_COOL:
            map.value = "cool"
            thermostatSetpoint = device.latestValue("coolingSetpoint")
            break
        case physicalgraph.zwave.commands.thermostatmodev2.ThermostatModeReport.MODE_AUTO:
            map.value = "auto"
            def temp = device.latestValue("temperature")
            def heatingSetpoint = device.latestValue("heatingSetpoint")
            def coolingSetpoint = device.latestValue("coolingSetpoint")
            if (temp && heatingSetpoint && coolingSetpoint) {
                if (temp < (heatingSetpoint + coolingSetpoint) / 2.0) {
                    thermostatSetpoint = heatingSetpoint
                } else {
                    thermostatSetpoint = coolingSetpoint
                }
            }
            break
    }
    state.lastTriedMode = map.value
    if (thermostatSetpoint) {
        [ createEvent(map), createEvent(name: "thermostatSetpoint", value: thermostatSetpoint, unit: getTemperatureScale()) ]
    } else {
        createEvent(map)
    }
}

def zwaveEvent(physicalgraph.zwave.commands.thermostatfanmodev3.ThermostatFanModeReport cmd) {
    def map = [name: "thermostatFanMode", displayed: false]
    switch (cmd.fanMode) {
        case physicalgraph.zwave.commands.thermostatfanmodev3.ThermostatFanModeReport.FAN_MODE_AUTO_LOW:
            map.value = "fanAuto"
            break
        case physicalgraph.zwave.commands.thermostatfanmodev3.ThermostatFanModeReport.FAN_MODE_LOW:
            map.value = "fanOn"
            break
        case physicalgraph.zwave.commands.thermostatfanmodev3.ThermostatFanModeReport.FAN_MODE_CIRCULATION:
            map.value = "fanCirculate"
            break
    }
    state.lastTriedFanMode = map.value
    createEvent(map)
}

def zwaveEvent(physicalgraph.zwave.commands.thermostatmodev2.ThermostatModeSupportedReport cmd) {
    def supportedModes = ""
    if(cmd.off) { supportedModes += "off " }
    if(cmd.heat) { supportedModes += "heat " }
    if(cmd.auxiliaryemergencyHeat) { supportedModes += "emergency heat " }
    if(cmd.cool) { supportedModes += "cool " }
    if(cmd.auto) { supportedModes += "auto " }

    state.supportedModes = supportedModes
    [ createEvent(name:"supportedModes", value: supportedModes, displayed: false),
      response(zwave.thermostatFanModeV3.thermostatFanModeSupportedGet()) ]
}

def zwaveEvent(physicalgraph.zwave.commands.thermostatfanmodev3.ThermostatFanModeSupportedReport cmd) {
    def supportedFanModes = ""
    if(cmd.auto) { supportedFanModes += "fanAuto " }
    if(cmd.low) { supportedFanModes += "fanOn " }
    if(cmd.circulation) { supportedFanModes += "fanCirculate " }

    state.supportedFanModes = supportedFanModes
    [ createEvent(name:"supportedFanModes", value: supportedModes, displayed: false),
      response(refresh()) ]
}

def zwaveEvent(physicalgraph.zwave.commands.basicv1.BasicReport cmd) {
    log.debug "Zwave event received: $cmd"
}

def zwaveEvent(physicalgraph.zwave.commands.batteryv1.BatteryReport cmd) {
    def map = [ name: "battery", unit: "%" ]
    if (cmd.batteryLevel == 0xFF) {
        map.value = 1
        map.descriptionText = "${device.displayName} battery is low"
        map.isStateChange = true
    } else {
        map.value = cmd.batteryLevel
    }
    state.lastbatt = now()
    createEvent(map)
}

def zwaveEvent(physicalgraph.zwave.Command cmd) {
    log.warn "Unexpected zwave command $cmd"
}

def refresh() {
    // Use encapsulation to differentiate refresh cmds from what the thermostat sends proactively on change
    def cmd = zwave.sensorMultilevelV2.sensorMultilevelGet()
    zwave.multiChannelV3.multiChannelCmdEncap(destinationEndPoint:1).encapsulate(cmd).format()
}

def nextRefreshQuery(name) {
    def cmd = null
    switch (name) {
        case "temperature":
            cmd = zwave.thermostatModeV2.thermostatModeGet()
            break
        case "thermostatMode":
            cmd = zwave.thermostatSetpointV1.thermostatSetpointGet(setpointType: 1)
            break
        case "heatingSetpoint":
            cmd = zwave.thermostatSetpointV1.thermostatSetpointGet(setpointType: 2)
            break
        case "coolingSetpoint":
            cmd = zwave.thermostatFanModeV3.thermostatFanModeGet()
            break
        case "thermostatFanMode":
            cmd = zwave.thermostatOperatingStateV2.thermostatOperatingStateGet()
            break
        case "thermostatOperatingState":
            // get humidity, multilevel sensor get to endpoint 2
            cmd = zwave.sensorMultilevelV2.sensorMultilevelGet()
            return zwave.multiChannelV3.multiChannelCmdEncap(destinationEndPoint:2).encapsulate(cmd).format()
        default: return null
    }
    zwave.multiChannelV3.multiChannelCmdEncap(destinationEndPoint:1).encapsulate(cmd).format()
}


def quickSetHeat(degrees) {
    setHeatingSetpoint(degrees, 500)
}

def setHeatingSetpoint(degrees, delay = 500) {
    setHeatingSetpoint(degrees.toDouble(), delay)
}

def setHeatingSetpoint(Double degrees, Integer delay = 500) {
    log.debug "setHeatingSetpoint($degrees, $delay)"
    def deviceScale = state.scale ?: 1
    def deviceScaleString = deviceScale == 2 ? "C" : "F"
    def locationScale = getTemperatureScale()
    def p = (state.precision == null) ? 1 : state.precision

    def convertedDegrees
    if (locationScale == "C" && deviceScaleString == "F") {
        convertedDegrees = celsiusToFahrenheit(degrees)
    } else if (locationScale == "F" && deviceScaleString == "C") {
        convertedDegrees = fahrenheitToCelsius(degrees)
    } else {
        convertedDegrees = degrees
    }

    delayBetween([
        zwave.thermostatSetpointV1.thermostatSetpointSet(setpointType: 1, scale: deviceScale, precision: p, scaledValue: convertedDegrees).format(),
        zwave.thermostatSetpointV1.thermostatSetpointGet(setpointType: 1).format()
    ], delay)
}

def quickSetCool(degrees) {
    setCoolingSetpoint(degrees, 500)
}

def setCoolingSetpoint(degrees, delay = 500) {
    setCoolingSetpoint(degrees.toDouble(), delay)
}

def setCoolingSetpoint(Double degrees, Integer delay = 500) {
    log.debug "setCoolingSetpoint($degrees, $delay)"
    def deviceScale = state.scale ?: 1
    def deviceScaleString = deviceScale == 2 ? "C" : "F"
    def locationScale = getTemperatureScale()
    def p = (state.precision == null) ? 1 : state.precision

    def convertedDegrees
    if (locationScale == "C" && deviceScaleString == "F") {
        convertedDegrees = celsiusToFahrenheit(degrees)
    } else if (locationScale == "F" && deviceScaleString == "C") {
        convertedDegrees = fahrenheitToCelsius(degrees)
    } else {
        convertedDegrees = degrees
    }

    delayBetween([
        zwave.thermostatSetpointV1.thermostatSetpointSet(setpointType: 2, scale: deviceScale, precision: p,     scaledValue: convertedDegrees).format(),
        zwave.thermostatSetpointV1.thermostatSetpointGet(setpointType: 2).format()
    ], delay)
}

def configure() {

 if (settings.tzOffset == null)
  {
    settings.tzOffset = "-8"
    log.debug "tzOffset was null ... set to -8!"
  }

    delayBetween([
        zwave.thermostatModeV2.thermostatModeSupportedGet().format(),
    ], 2300)
}

def modes() {
    ["off", "heat", "cool", "auto", "emergency heat"]
}

def switchMode() {
    def currentMode = device.currentState("thermostatMode")?.value
    def lastTriedMode = state.lastTriedMode ?: currentMode ?: "off"
    def supportedModes = getDataByName("supportedModes")
    def modeOrder = modes()
    def next = { modeOrder[modeOrder.indexOf(it) + 1] ?: modeOrder[0] }
    def nextMode = next(lastTriedMode)
    if (supportedModes?.contains(currentMode)) {
        while (!supportedModes.contains(nextMode) && nextMode != "off") {
            nextMode = next(nextMode)
        }
    }
    state.lastTriedMode = nextMode
    delayBetween([
        zwave.thermostatModeV2.thermostatModeSet(mode: modeMap[nextMode]).format(),
        zwave.thermostatModeV2.thermostatModeGet().format()
    ], 1000)
}

def switchToMode(nextMode) {
    def supportedModes = getDataByName("supportedModes")
    if(supportedModes && !supportedModes.contains(nextMode)) log.warn "thermostat mode '$nextMode' is not supported"
    if (nextMode in modes()) {
        state.lastTriedMode = nextMode
        "$nextMode"()
    } else {
        log.debug("no mode method '$nextMode'")
    }
}

def switchFanMode() {
    def currentMode = device.currentState("thermostatFanMode")?.value
    def lastTriedMode = state.lastTriedFanMode ?: currentMode ?: "off"
    def supportedModes = getDataByName("supportedFanModes") ?: "fanAuto fanOn"
    def modeOrder = ["fanAuto", "fanCirculate", "fanOn"]
    def next = { modeOrder[modeOrder.indexOf(it) + 1] ?: modeOrder[0] }
    def nextMode = next(lastTriedMode)
    while (!supportedModes?.contains(nextMode) && nextMode != "fanAuto") {
        nextMode = next(nextMode)
    }
    switchToFanMode(nextMode)
}

def switchToFanMode(nextMode) {
    def supportedFanModes = getDataByName("supportedFanModes")
    if(supportedFanModes && !supportedFanModes.contains(nextMode)) log.warn "thermostat mode '$nextMode' is not supported"

    def returnCommand
    if (nextMode == "fanAuto") {
        returnCommand = fanAuto()
    } else if (nextMode == "fanOn") {
        returnCommand = fanOn()
    } else if (nextMode == "fanCirculate") {
        returnCommand = fanCirculate()
    } else {
        log.debug("no fan mode '$nextMode'")
    }
    if(returnCommand) state.lastTriedFanMode = nextMode
    returnCommand
}

def getDataByName(String name) {
    state[name] ?: device.getDataValue(name)
}

def getModeMap() { [
    "off": 0,
    "heat": 1,
    "cool": 2,
    "auto": 3,
    "emergency heat": 4
]}

def setThermostatMode(String value) {
    delayBetween([
        zwave.thermostatModeV2.thermostatModeSet(mode: modeMap[value]).format(),
        zwave.thermostatModeV2.thermostatModeGet().format()
    ], standardDelay)
}

def getFanModeMap() { [
    "auto": 0,
    "on": 1,
    "circulate": 6
]}

def setThermostatFanMode(String value) {
    delayBetween([
        zwave.thermostatFanModeV3.thermostatFanModeSet(fanMode: fanModeMap[value]).format(),
        zwave.thermostatFanModeV3.thermostatFanModeGet().format()
    ], standardDelay)
}

def off() {
    delayBetween([
        zwave.thermostatModeV2.thermostatModeSet(mode: 0).format(),
        zwave.thermostatModeV2.thermostatModeGet().format()
    ], standardDelay)
}

def heat() {
    delayBetween([
        zwave.thermostatModeV2.thermostatModeSet(mode: 1).format(),
        zwave.thermostatModeV2.thermostatModeGet().format()
    ], standardDelay)
}

def emergencyHeat() {
    delayBetween([
        zwave.thermostatModeV2.thermostatModeSet(mode: 4).format(),
        zwave.thermostatModeV2.thermostatModeGet().format()
    ], standardDelay)
}

def cool() {
    delayBetween([
        zwave.thermostatModeV2.thermostatModeSet(mode: 2).format(),
        zwave.thermostatModeV2.thermostatModeGet().format()
    ], standardDelay)
}

def auto() {
    delayBetween([
        zwave.thermostatModeV2.thermostatModeSet(mode: 3).format(),
        zwave.thermostatModeV2.thermostatModeGet().format()
    ], standardDelay)
}

def fanOn() {
    delayBetween([
        zwave.thermostatFanModeV3.thermostatFanModeSet(fanMode: 1).format(),
        zwave.thermostatFanModeV3.thermostatFanModeGet().format()
    ], standardDelay)
}

def fanAuto() {
    delayBetween([
        zwave.thermostatFanModeV3.thermostatFanModeSet(fanMode: 0).format(),
        zwave.thermostatFanModeV3.thermostatFanModeGet().format()
    ], standardDelay)
}

def fanCirculate() {
    delayBetween([
        zwave.thermostatFanModeV3.thermostatFanModeSet(fanMode: 6).format(),
        zwave.thermostatFanModeV3.thermostatFanModeGet().format()
    ], standardDelay)
}

private getStandardDelay() {
    1000
}


def coolLevelUp(){
    int nextLevel = device.currentValue("coolingSetpoint") + 1
    
    if( nextLevel > 99){
      nextLevel = 99
    }
    log.debug "Setting cool set point up to: ${nextLevel}"
    setCoolingSetpoint(nextLevel)
}

def coolLevelDown(){
    int nextLevel = device.currentValue("coolingSetpoint") - 1
    
    if( nextLevel < 50){
      nextLevel = 50
    }
    log.debug "Setting cool set point down to: ${nextLevel}"
    setCoolingSetpoint(nextLevel)
}

def heatLevelUp(){
    int nextLevel = device.currentValue("heatingSetpoint") + 1
    
    if( nextLevel > 90){
      nextLevel = 90
    }
    log.debug "Setting heat set point up to: ${nextLevel}"
    setHeatingSetpoint(nextLevel)
}

def heatLevelDown(){
    int nextLevel = device.currentValue("heatingSetpoint") - 1
    
    if( nextLevel < 40){
      nextLevel = 40
    }
    log.debug "Setting heat set point down to: ${nextLevel}"
    setHeatingSetpoint(nextLevel)
}

def input()
{
 if (settings.tzOffset == null)
  {
    settings.tzOffset = "-8"
    log.debug "tzOffset was null ... set to -8!"
  }
}