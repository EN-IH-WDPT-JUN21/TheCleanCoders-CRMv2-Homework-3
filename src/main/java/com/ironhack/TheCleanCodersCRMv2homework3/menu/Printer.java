package com.ironhack.TheCleanCodersCRMv2homework3.menu;

import com.ironhack.TheCleanCodersCRMv2homework3.dao.Account;
import com.ironhack.TheCleanCodersCRMv2homework3.output.Style;
import com.ironhack.TheCleanCodersCRMv2homework3.repository.*;
import com.ironhack.TheCleanCodersCRMv2homework3.utils.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Printer {

    public void print(String text) {
        System.out.println(text);
    }

    public void printTypoInfo(String wrongWord) {
        System.out.println("Incorrect command. Possible typo in the word \"" + wrongWord + "\". Please try again.");
    }

    public void printExportErrorInfo(String filename) {
        System.out.println("There was an error while exporting data to file " + filename);
    }

    public void printImportErrorInfo(String filename) {
        System.out.println("There was an error while importing data from file " + filename);
    }   

    public void welcomeMessage(){
        print("\n\n============================================================");
        print("                       Welcome to\n");
        print("                     theCleanCoders\n");
        print("            ########   #######     ##        ##");
        print("            ########   ########    ###      ###");
        print("            ##         ##     ##   ####    ####");
        print("            ##         ##     ##   ## ##  ## ##");
        print("            ##         ##     ##   ##   ##   ##");
        print("            ##         ########    ##   ##   ##");
        print("            ##         #######     ##        ##");
        print("            ##         ##   ##     ##        ##");
        print("            ##         ##    ##    ##        ##");
        print("            ########   ##     ##   ##        ##");
        print("            ########   ##      ##  ##        ##");
        print("\n\nTo start using our cleanCRM, type the commands below. Type \"HELP\" for instructions");
      
    }
      
    public void helpPage(){
        System.out.println("=================================================");
        System.out.println("            The cleanCRM Help page");
        System.out.println("=================================================");

        System.out.println("\n              Possible Commands:\n " );
        System.out.println(Style.LIGHT_PURPLE + " NEW <Object type>                      " + Style.LIGHT_GRAY + "Creates a new Object");
        System.out.println(Style.LIGHT_PURPLE + " SHOW <Object type>                     " + Style.LIGHT_GRAY + "Lists all Objects of the selected type");
        System.out.println(Style.LIGHT_PURPLE + " LOOKUP <Object type> <Id Number>       " + Style.LIGHT_GRAY + "Display the selected Object type with the indicated Id Number");
        System.out.println(Style.LIGHT_PURPLE + " CONVERT <LEAD Id number>               " + Style.LIGHT_GRAY + "Converts the selected LEAD in CONTACT, OPPORTUNITY and ACCOUNT");
        System.out.println(Style.LIGHT_PURPLE + " CLOSE-WON <OPPORTUNITY Id Number>      " + Style.LIGHT_GRAY + "Changes the selected OPPORTUNITY status to CLOSE-WON");
        System.out.println(Style.LIGHT_PURPLE + " CLOSE-LOST <OPPORTUNITY Id Number>     " + Style.LIGHT_GRAY + "Changes the selected OPPORTUNITY status to CLOSE-LOST");
        System.out.println(Style.LIGHT_PURPLE + " OPEN <OPPORTUNITY Id Number>           " + Style.LIGHT_GRAY + "Changes the selected OPPORTUNITY status to OPEN");
        System.out.println(Style.LIGHT_PURPLE + " POPULATE                               " + Style.LIGHT_GRAY + "Populate the database with some sample data");
        System.out.println(Style.LIGHT_PURPLE + " HELP                                   " + Style.LIGHT_GRAY + "Displays this help info");
        System.out.println(Style.LIGHT_PURPLE + " EXIT                                   " + Style.LIGHT_GRAY + "Terminates the cleanCRM program" + Style.DEFAULT);
        System.out.println("\n\n             Object Types Available:\n ");
        System.out.println(Style.LIGHT_PURPLE + " LEAD / CONTACT / OPPORTUNITY / ACCOUNT / SALESREP\n");
        System.out.println(Style.LIGHT_GRAY + "*All Object types accepted in plural (e.g. OPPORTUNITY and OPPORTUNITIES are both accepted forms)");
        System.out.println("*All commands are case-insensitive\n" + Style.DEFAULT);
    }

}
