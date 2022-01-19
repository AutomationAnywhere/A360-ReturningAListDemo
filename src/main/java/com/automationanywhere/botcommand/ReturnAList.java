package com.automationanywhere.botcommand;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;

import java.util.ArrayList;
import java.util.List;

import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.LIST;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

//BotCommand makes a class eligible for being considered as an action.
@BotCommand

//CommandPks adds required information to be dispalable on GUI.
@CommandPkg(
        //Unique name inside a package and label to display.
        name = "returnalist", label = "Return a List",
        node_label = "Return a List", description = "Returns a list of the provided strings", icon = "pkg.svg",

        //Return type information. return_type ensures only the right kind of variable is provided on the UI.
        return_label = "New list of Strings", return_type = LIST, return_required = true)
public class ReturnAList {

    //Messages read from full qualified property file name and provide i18n capability.
    private static final Messages MESSAGES = MessagesFactory
            .getMessages("com.automationanywhere.botcommand.samples.messages");

    //Identify the entry point for the action. Returns a Value<String> because the return type is String.
    @Execute

    public ListValue<String> action(
            //Idx 1 would be displayed first, with a text box for entering the value.
            @Idx(index = "1", type = TEXT)
            //UI labels.
            @Pkg(label = "Field 1 for List")
            //Ensure that a validation error is thrown when the value is null.
            @NotEmpty
                    String firstString,
            @Idx(index = "2", type = TEXT)
            @Pkg(label = "Field 2 for List")
            @NotEmpty
                    String secondString) {

        //Create new AA ListValue variable type
        ListValue<String> sampleListValue = new ListValue<>();
        //Create new Java ArrayList
        List<Value> values = new ArrayList<Value>();
        try {
            //Add Values to Java Array list as Automation Anywhere types (StringValue here, but could be other supported types)
            values.add(new StringValue(firstString));
            values.add(new StringValue(secondString));
            //Set the AA ListValue variable to the Java Arraylist
            sampleListValue.set(values);
        } catch (Exception e) {
            throw new BotCommandException("Error occurred in creating list: " + e.toString());
        }

        //Return StringValue.
        return sampleListValue;

    }
}
