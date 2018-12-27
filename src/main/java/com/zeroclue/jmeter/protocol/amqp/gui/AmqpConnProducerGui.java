package com.zeroclue.jmeter.protocol.amqp.gui;

import com.zeroclue.jmeter.protocol.amqp.AMQPPublisher;
import com.zeroclue.jmeter.protocol.amqp.AmqpConnProducer;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.config.gui.ArgumentsPanel;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jorphan.gui.JLabeledChoice;
import org.apache.jorphan.gui.JLabeledTextArea;
import org.apache.jorphan.gui.JLabeledTextField;

import javax.swing.*;
import java.awt.*;

/**
 * AMQP Sampler
 *
 * This class is responsible for ensuring that the Sampler data is kept in step
 * with the GUI.
 *
 * The GUI class is not invoked in non-GUI mode, so it should not perform any
 * additional setup that a test would need at run-time
 *
 */
public class AmqpConnProducerGui extends AMQPSamplerGui {

    private static final long serialVersionUID = 1L;

    private JPanel mainPanel;

    /*
    private static final String[] CONFIG_CHOICES = {"File", "Static"};
    private final JLabeledRadio configChoice = new JLabeledRadio("Message Source", CONFIG_CHOICES);
    private final FilePanel messageFile = new FilePanel("Filename", ALL_FILES);
    */
    private JLabeledTextArea message = new JLabeledTextArea("Message Content");
    private JLabeledTextField messageRoutingKey = new JLabeledTextField("Routing Key");
    private JLabeledTextField messageType = new JLabeledTextField("Message Type");
    private JLabeledTextField replyToQueue = new JLabeledTextField("Reply-To Queue");
    private JLabeledTextField correlationId = new JLabeledTextField("Correlation Id");
    private JLabeledTextField contentType = new JLabeledTextField("ContentType");
    private JLabeledTextField messageId = new JLabeledTextField("Message Id");


    private JCheckBox persistent = new JCheckBox("Persistent?", AMQPPublisher.DEFAULT_PERSISTENT);
    private JCheckBox useTx = new JCheckBox("Use Transactions?", AMQPPublisher.DEFAULT_USE_TX);

    private ArgumentsPanel headers = new ArgumentsPanel("Headers");

    private JLabeledTextField sleepIters = new JLabeledTextField("Sleep Iterations");
    private JLabeledTextField sleepTime = new JLabeledTextField("Sleep Time:");


    public AmqpConnProducerGui(){
        init();
    }

    /**
     * {@inheritDoc}
     */
    public String getLabelResource() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getStaticLabel() {
        return "Amqp Conn Producer";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void configure(TestElement element) {
        super.configure(element);
        if (!(element instanceof AmqpConnProducer)) return;
        AmqpConnProducer sampler = (AmqpConnProducer) element;

        persistent.setSelected(sampler.getPersistent());
        useTx.setSelected(sampler.getUseTx());

        messageRoutingKey.setText(sampler.getMessageRoutingKey());
        messageType.setText(sampler.getMessageType());
        replyToQueue.setText(sampler.getReplyToQueue());
        contentType.setText(sampler.getContentType());
        correlationId.setText(sampler.getCorrelationId());
        messageId.setText(sampler.getMessageId());
        message.setText(sampler.getMessage());
        sleepIters.setText(String.valueOf(sampler.getSleepIters()));
        sleepTime.setText(String.valueOf(sampler.getSleepTime()));
        configureHeaders(sampler);
    }

    /**
     * {@inheritDoc}
     */
    public TestElement createTestElement() {
        AmqpConnProducer sampler = new AmqpConnProducer();
        modifyTestElement(sampler);
        return sampler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyTestElement(TestElement te) {
        AmqpConnProducer sampler = (AmqpConnProducer) te;
        sampler.clear();
        configureTestElement(sampler);

        super.modifyTestElement(sampler);

        sampler.setPersistent(persistent.isSelected());
        sampler.setUseTx(useTx.isSelected());

        sampler.setMessageRoutingKey(messageRoutingKey.getText());
        sampler.setMessage(message.getText());
        sampler.setMessageType(messageType.getText());
        sampler.setReplyToQueue(replyToQueue.getText());
        sampler.setCorrelationId(correlationId.getText());
        sampler.setContentType(contentType.getText());
        sampler.setMessageId(messageId.getText());
        sampler.setHeaders((Arguments) headers.createTestElement());
        Integer sleepItersVal = 10;
        if(NumberUtils.isNumber(sleepIters.getText())){
            sleepItersVal = Integer.parseInt(sleepIters.getText());
        }
        sampler.setSleepIters(sleepItersVal);
        Integer sleepTimeVal = 10;
        if(NumberUtils.isNumber(sleepTime.getText())){
            sleepTimeVal = Integer.parseInt(sleepTime.getText());
        }
        sampler.setSleepTime(sleepTimeVal);
    }

    @Override
    protected void setMainPanel(JPanel panel){
        mainPanel = panel;
    }

    /*
     * Helper method to set up the GUI screen
     */
    @Override
    protected final void init() {
        super.init();
        persistent.setPreferredSize(new Dimension(100, 25));
        useTx.setPreferredSize(new Dimension(100, 25));
        sleepIters.setPreferredSize(new Dimension(100,25));
        sleepTime.setPreferredSize(new Dimension(100,25));
        messageRoutingKey.setPreferredSize(new Dimension(100, 25));
        messageType.setPreferredSize(new Dimension(100, 25));
        replyToQueue.setPreferredSize(new Dimension(100, 25));
        correlationId.setPreferredSize(new Dimension(100, 25));
        contentType.setPreferredSize(new Dimension(100, 25));
        messageId.setPreferredSize(new Dimension(100, 25));
        message.setPreferredSize(new Dimension(400, 150));

        mainPanel.add(persistent);
        mainPanel.add(useTx);
        mainPanel.add(sleepIters);
        mainPanel.add(sleepTime);
        mainPanel.add(messageRoutingKey);
        mainPanel.add(messageType);
        mainPanel.add(replyToQueue);
        mainPanel.add(correlationId);
        mainPanel.add(contentType);
        mainPanel.add(messageId);
        mainPanel.add(headers);
        mainPanel.add(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearGui() {
        super.clearGui();
        persistent.setSelected(AmqpConnProducer.DEFAULT_PERSISTENT);
        useTx.setSelected(AmqpConnProducer.DEFAULT_USE_TX);
        messageRoutingKey.setText("");
        messageType.setText("");
        replyToQueue.setText("");
        correlationId.setText("");
        contentType.setText("");
        messageId.setText("");
        headers.clearGui();
        message.setText("");
        sleepIters.setText("10");
        sleepTime.setText("1000");
    }

    private void configureHeaders(AmqpConnProducer sampler)
    {
        Arguments sampleHeaders = sampler.getHeaders();
        if (sampleHeaders != null) {
            headers.configure(sampleHeaders);
        } else {
            headers.clearGui();
        }
    }
}