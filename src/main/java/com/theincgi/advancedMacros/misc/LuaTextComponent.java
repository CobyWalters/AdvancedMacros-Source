package com.theincgi.advancedMacros.misc;

import org.luaj.vm2_v3_0_1.LuaValue;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentBase;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.util.text.event.HoverEvent.Action;

public class LuaTextComponent extends TextComponentBase{
	private String text;
	private LuaValue action;
	private boolean allowHover;
	
	public LuaTextComponent(String text, LuaValue action, boolean allowHover) {
		super();
		this.text = text;
		this.action = action;
		this.allowHover = allowHover;
		getStyle().setClickEvent( new LuaTextComponentClickEvent(action, this));
		if(action.istable() && allowHover) {
			getStyle().setHoverEvent(new HoverEvent(Action.SHOW_TEXT, Utils.toTextComponent(text, null, false).a));
		}
			
	}

	
	@Override
	public String getUnformattedComponentText() {
		return text;
	}

	@Override
	public ITextComponent createCopy() {
		return new LuaTextComponent(this.text, action, allowHover);
	}

}