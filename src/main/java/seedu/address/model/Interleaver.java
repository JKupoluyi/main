package seedu.address.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import seedu.address.model.person.Person;
import seedu.address.model.person.modulelist.Module;
import seedu.address.model.person.timetable.Activity;
import seedu.address.model.person.timetable.TimeTable;

/**
 * All interleaving is done here
 */
public class Interleaver {

    private static final double FOCUS_PERIOD = 0.5; //in hours for now
    //private static final int FOCUS_PERIOD = 30; //in mins. Default and minimum is 30. Max is 120.

    /**
     * Controls this class methods
     */
    public static TimeTable interleave(Person person) {
        ArrayList<Module> modules = person.getModules().getModuleList();
        ArrayList<Stack> blocksOfModules = new ArrayList<>();
        Iterator i = modules.iterator();
        while (i.hasNext()) {
            Module currentMod = (Module) (i.next());
            Stack<String> moduleBlocks = new Stack<>();
            for (int k = 0; k < (currentMod.getSelfStudyHours() / FOCUS_PERIOD); k++) {
                moduleBlocks.push(currentMod.getActivityName());
            }
            blocksOfModules.add(moduleBlocks);
        }
        i = blocksOfModules.iterator();
        while (i.hasNext()) {
            Stack<String> currentMod = (Stack<String>) i.next();
            while (!currentMod.empty()) {
                for (int day = 0; day < TimeTable.NUM_DAYS; day++) { // iterate timetable for free slots
                    for (int time = 16; time < TimeTable.NUM_30MINS_BLOCKS; time++) { // starting from 0800
                        if (person.getTimeTable().getTimeTableArray()[day][time] == null) {
                            person.getTimeTable().add((new Activity("Study " + currentMod.pop())), day, time);
                            break;
                        }
                    }
                    if (currentMod.empty()) {
                        break;
                    }
                }
            }
        }
        return person.getTimeTable();
    }

    //dont need generate method anymore?

}
