isPistolOut = true
isPrimaryOut = false
isNeitherGunOut = false
isThrowCocked = false 

EnablePrimaryMouseButtonEvents(true)


function OnEvent(event, arg)
	if (event == "MOUSE_BUTTON_PRESSED" and arg == 2) then

		if isPistolOut == false then
	    	OutputLogMessage("My pistol was not out. Drawing it.\n")
			drawSecondary()
		end
		
		fireWeapon()	
	elseif (event == "MOUSE_BUTTON_RELEASED" and arg == 2) then
		if isPistolOut == false then
	    		OutputLogMessage("My pistol was not out. Drawing it.\n")
			drawSecondary()
		end

		stopFiringWeapon()
	elseif (event == "MOUSE_BUTTON_PRESSED" and arg == 1) then
        if isPrimaryOut == false then
            drawPrimary()
            OutputLogMessage("Drew Primary\n")
        end

        fireWeapon();

        OutputLogMessage("Mouse Button 1 was pressed\n")
	elseif (event == "MOUSE_BUTTON_RELEASED" and arg == 1) then
        if isPrimaryOut == false then
            drawPrimary()
            OutputLogMessage("Drew Primary\n")
        end

        stopFiringWeapon()
	elseif (event == "MOUSE_BUTTON_PRESSED" and arg == 5) then
		drawMedkit()
		Sleep(500)--fiddle with this
		useMedkit()
	elseif (event == "MOUSE_BUTTON_RELEASED" and arg == 5) then
		stopUsingMedkit()
	elseif (event == "MOUSE_BUTTON_PRESSED" and arg == 7) then
		drawThrow()
		Sleep(400)--fiddle with this
		cockThrow()
	elseif (event == "MOUSE_BUTTON_RELEASED" and arg == 7) then
		if isThrowCocked then
			throw()
		end
	elseif (event == "MOUSE_BUTTON_PRESSED" and arg == 8) then
		drawBuff()
		Sleep(500)--fiddle with this
		useBuff()
	elseif (event == "MOUSE_BUTTON_RELEASED" and arg ==  8) then
	end
    OutputLogMessage("event = %s, arg = %s\n", event, arg)
end

function drawBuff()
	PressKey("5")
	ReleaseKey("5")
	isNeitherGunOut = true
end

function useBuff() 
	PressKey("slash")
	sleep(100)
	ReleaseKey("slash")
	Sleep(300)
isNeitherGunOut = false
	if isPrimaryOut then
		drawPrimary()
	else
		drawSecondary()
	end
end

function throw() 
	ReleaseKey("slash")
	Sleep(300)
	isThrowCocked = false
isNeitherGunOut = false
	if isPrimaryOut then
		drawPrimary()
	else
		drawSecondary()
	end
end

function drawThrow()
	PressKey("3")
	ReleaseKey("3")
	isNeitherGunOut = true
end

function cockThrow()
	PressKey("slash")
	isThrowCocked = true
end

function stopUsingMedkit() 
	ReleaseKey("slash")
end

function drawMedkit() 
	PressKey("4")
	ReleaseKey("4")
	isNeitherGunOut = true
end

function useMedkit() 
	PressKey("slash")
end


function fireWeapon() 
	PressKey("slash")
	OutputLogMessage("Weapon Firing\n")		
end

function stopFiringWeapon() 
	ReleaseKey("slash")
	OutputLogMessage("Weapon Stopping\n")		
end 



function drawSecondary() 
	PressKey("2")
	Sleep(15)
	ReleaseKey("2")
	isPistolOut = true
	isPrimaryOut = false
	Sleep(15)
	OutputLogMessage("Secondary Out\n")
end

function drawPrimary() 
	PressKey("1")
	Sleep(15)
	ReleaseKey("1")
	isPistolOut = false	
	isPrimaryOut = true
	OutputLogMessage("Primary Out\n")

	
end