package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Set;

import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * The command handler for {@code list contact} command
 */
public class ListPersonCommand extends ListCommand {
    public static final String MESSAGE_SUCCESS = "Listed all contacts";
    public static final String SECONDARY_COMMAND_WORD = "contact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + SECONDARY_COMMAND_WORD
            + ": Lists all contacts in the address book";

    private final Set<Tag> tags;

    /**
     * Creates an AddPersonCommand to add the specified {@code Person}
     */
    public ListPersonCommand(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (tags.isEmpty()) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        } else {
            model.updateFilteredPersonList(person -> {
                for (Tag tag : tags) {
                    if (person.containsTag(tag)) {
                        return true;
                    }
                }
                return false;
            });
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
