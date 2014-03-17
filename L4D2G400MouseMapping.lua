isPistolOut = false
isPrimaryOut = false
firedFromWithin = false
EnablePrimaryMouseButtonEvents(true)

function OnEvent(event, arg)
	if (event == "MOUSE_BUTTON_PRESSED" and arg == 2) then
	    OutputLogMessage("Pressed alternate fire\n")

		if isPistolOut == false then
	    	OutputLogMessage("My pistol waszzzz not out. Drawing it.\n")
			drawSecondary()
		end
	    	OutputLogMessage("Firing alternate weapon\n")

		fireWeapon()
	elseif (event == "MOUSE_BUTTON_PRESSED" and arg == 1) then
        if isPrimaryOut == false then
            drawPrimary()
            OutputLogMessage("Drew Primary\n")
        end
        fireWeapon();


        OutputLogMessage("Mouse Button 1 was pressed\n")
    end
    OutputLogMessage("event = %s, arg = %s\n", event, arg)
end



function fireWeapon()
	firedFromWithin = true
	PressKey("slash")
	Sleep(15)
	ReleaseKey("slash")
	Sleep(15)
	OutputLogMessage("Weapon Fired\n")

end


function drawSecondary()
	PressKey("2")
	Sleep(15)
	ReleaseKey("2")
	isPistolOut = true
	isPrimaryOut = false
	Sleep(15)
	OutputLogMessage("Secondary Out. is pistol out? %s1\n1", tostring(isPistolOut))
end

function drawPrimary()
	PressKey("1")
	Sleep(15)
	ReleaseKey("1")
	isPistolOut = false
	isPrimaryOut = true
	Sleep(15)
	OutputLogMessage("Primary Out. is primary out? %s\n", tostring(isPrimaryOut))
end