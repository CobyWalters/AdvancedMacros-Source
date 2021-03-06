package com.theincgi.advancedMacros.lua.functions;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import org.luaj.vm2_v3_0_1.LuaError;
import org.luaj.vm2_v3_0_1.LuaValue;
import org.luaj.vm2_v3_0_1.lib.OneArgFunction;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.theincgi.advancedMacros.AdvancedMacros;

public class IsKeyHeld  extends OneArgFunction{
	@Override
	public LuaValue call(LuaValue arg0) {
		String s = arg0.checkjstring();
		switch (s) {
		case "LMB":
			return LuaValue.valueOf(Mouse.isButtonDown(0));
		case "RMB":
			return LuaValue.valueOf(Mouse.isButtonDown(1));
		case "MMB":
			return LuaValue.valueOf(Mouse.isButtonDown(2));
		default:
			if(s.startsWith("MOUSE:")) {
				try {
				int index = Integer.parseInt(s.substring(s.lastIndexOf(":")+1));
				return LuaValue.valueOf(Mouse.isButtonDown(index));
				}catch (Exception e) {
					throw new LuaError("Could not get MOUSE:"+INDEX);
				}
			}else{
				int in = Keyboard.getKeyIndex(s);
				if(in==Keyboard.KEY_NONE)
					throw new LuaError("Could not get key \""+s+"\"");
				return LuaValue.valueOf(Keyboard.isKeyDown(in));
			}
		}
	}
}