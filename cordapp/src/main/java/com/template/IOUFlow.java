package com.template;

import co.paralleluniverse.fibers.Suspendable;
import net.corda.core.contracts.Command;
import net.corda.core.flows.FlowException;
import net.corda.core.flows.FlowLogic;
import net.corda.core.flows.InitiatingFlow;
import net.corda.core.flows.StartableByRPC;
import net.corda.core.identity.Party;
import net.corda.core.utilities.ProgressTracker;
import org.jetbrains.annotations.Nullable;

// ******************
// * Initiator flow *
// ******************
@InitiatingFlow
@StartableByRPC
public class IOUFlow extends FlowLogic<Void> {

    private final Integer iouValue;
    private final Party otherParty;

    // The progress tracker provides checkpoints indicating the progress of the flow to observers.
    private final ProgressTracker progressTracker = new ProgressTracker();

    public IOUFlow(Integer iouValue, Party otherParty){
        this.iouValue = iouValue;
        this.otherParty = otherParty;
    }

    @Override
    public ProgressTracker getProgressTracker() {
        return progressTracker;
    }

    // The flow logic is encapsulated within the call() method.
    @Override
    public Void call() throws FlowException {
        // We retrieve the notary identity from the network map.
        Party notary = getServiceHub().getNetworkMapCache().getNotaryIdentities().get(0);

        // We create the transaction components.
        IOUState outputState = new IOUState(iouValue, getOurIdentity(), otherParty);
        Command command = new Command<>(new TemplateContract.Commands.Action(), getOurIdentity().getOwningKey());

        
        return null;
    }
}
