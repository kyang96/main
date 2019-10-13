package wallet.logic.command;

import wallet.model.Wallet;
import wallet.model.record.Expense;
import wallet.model.contact.Contact;


/**
 * EditCommand Class deals with commands that involves
 * in editing a specific object
 * in the a specific list.
 */
public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = "Usage for edit command."
            + "\nExample: " + COMMAND_WORD + " expense 2 /a 4.50 /c food /r daily"
            + "\nExample: " + COMMAND_WORD + " expense 2 /d lunch /a 9 /c Food /r no";
    public static final String MESSAGE_SUCCESS_EDIT_EXPENSE = "Successfully edited this expense:";
    public static final String MESSAGE_SUCCESS_EDIT_CONTACT = "Successfully edited this contact:";

    private Expense expense;
    private Contact contact;

    /**
     * Constructs the EditCommand object with Expense object.
     *
     * @param expense The Expense Object.
     */
    public EditCommand(Expense expense) {
        this.expense = expense;
    }

    /**
     * Constructs the EditCommand object with Contact object.
     *
     * @param contact The Contact Object.
     */
    public EditCommand(Contact contact) {
        this.contact = contact;
    }

    @Override
    public boolean execute(Wallet wallet) {
        if (expense != null) {
            int index = wallet.getExpenseList().findIndexWithId(expense.getId());
            Expense currentExpense = wallet.getExpenseList().getExpense(index);
            if (expense.getRecFrequency() == null || !expense.getRecFrequency().equals("")) {
                currentExpense.setRecurring(expense.isRecurring());
                currentExpense.setRecFrequency(expense.getRecFrequency());
            }
            if (expense.getAmount() != 0.0) {
                currentExpense.setAmount(expense.getAmount());
            }
            if (expense.getCategory() != null) {
                currentExpense.setCategory(expense.getCategory());
            }
            if (expense.getDescription() != null) {
                currentExpense.setDescription(expense.getDescription());
            }
            wallet.getExpenseList().editExpense(index, currentExpense);
            wallet.getExpenseList().setModified(true);
            System.out.println(MESSAGE_SUCCESS_EDIT_EXPENSE);
            System.out.println(currentExpense.toString());
        } else if (contact != null) {

            int index = wallet.getContactList().findContactWithId(contact.getId());
            if (index != -1) {
                Contact currentContact = wallet.getContactList().getContact(index);
                if (contact.getName() != null) {
                    currentContact.setName(contact.getName());
                } else if (contact.getDetail() != null) {
                    currentContact.setDetail(contact.getDetail());
                } else if (contact.getPhoneNum() != null) {
                    currentContact.setPhoneNum(contact.getPhoneNum());
                }

                wallet.getContactList().editContact(index, currentContact);
                wallet.getContactList().setModified(true);
                System.out.println(MESSAGE_SUCCESS_EDIT_CONTACT);
                System.out.println(currentContact.toString());
            }
        }

        return false;
    }
}
