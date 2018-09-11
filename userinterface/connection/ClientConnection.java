package userinterface.connection;

import org.msgpack.core.MessagePack;
import org.msgpack.core.MessagePackException;
import org.msgpack.core.MessagePacker;
import org.msgpack.core.MessageUnpacker;
import org.msgpack.core.buffer.ArrayBufferInput;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQException;
import userinterface.EventFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

public class ClientConnection<E> implements AutoCloseable {

    private final ZContext context;
    private final ZMQ.Socket socket;
    private final ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
    private final ArrayBufferInput inputBuffer = new ArrayBufferInput(new byte[0]);
    private final MessagePacker packer;
    private final MessageUnpacker unpacker;
    private final EventFactory<? extends E> eventFactory;
    private boolean closed;

    public ClientConnection(String host, int port, EventFactory<? extends E> eventFactory) {
        Objects.requireNonNull(host);
        if (port <= 1023) {
            throw new IllegalArgumentException("Portnummer zu niedrig! (Siehe https://de.wikipedia.org/wiki/Transmission_Control_Protocol#Allgemeines)");
        } else if (port >= 65535) {
            throw new IllegalArgumentException("Portnummer zu groß! (Siehe https://de.wikipedia.org/wiki/Transmission_Control_Protocol#Allgemeines");
        } else {
            this.eventFactory = (EventFactory) Objects.requireNonNull(eventFactory);
            this.unpacker = MessagePack.newDefaultUnpacker(this.inputBuffer);
            this.packer = MessagePack.newDefaultPacker(this.outputBuffer);
            this.context = new ZContext();
            this.socket = this.context.createSocket(5);
            this.socket.setReceiveTimeOut(-1); //TODO check if works?
            this.socket.connect(String.format("tcp://%s:%d", host, port));
        }
    }

    public void close() {
        if (!this.closed) {
            this.context.destroy();
            this.closed = true;
        }

    }

    public final E nextEvent() {
        byte[] data;
        try {
            data = this.socket.recv();
        } catch (ZMQException var11) {
            if (var11.getErrorCode() == 65) {
                throw new CommException("Die andere Seite der Verbindung ist bereits geschlossen!", var11);
            }

            throw new CommException(String.format("Clientseitiger Commlibfehler %d!", var11.getErrorCode()), var11);
        }

        if (data == null) {
            throw new TimeoutException();

        } else {
            this.inputBuffer.reset(data);

            try {
                int type = this.unpacker.unpackInt();
                int value;
                String teamName;
                String message;
                int x;
                int y;
                String tile;
                switch (type) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        x = this.unpacker.unpackInt();
                        y = this.unpacker.unpackInt();
                        tile = this.unpacker.unpackString();
                        teamName = this.unpacker.unpackString();
                        return this.eventFactory.createMove(x, y, tile, teamName);
                    case 3:
                        return this.eventFactory.createNewGame();
                    case 4:
                        value = this.unpacker.unpackInt();
                        message = this.unpacker.unpackString();
                        return this.eventFactory.createGameFinish(value, message);
                    case 5:
                        value = this.unpacker.unpackInt();
                        return this.eventFactory.createGamePause(value);
                    case 6:
                    case 7:
                        break;
                    case 8:
                        message = this.unpacker.unpackString();
                        return this.eventFactory.createChat(message);
                    case 9:
                        teamName = this.unpacker.unpackString();
                        tile = this.unpacker.unpackString();
                        return this.eventFactory.createPlayer(teamName, tile);
                    case 10:
                        teamName = this.unpacker.unpackString();
                        value = this.unpacker.unpackInt();
                        System.out.println("logged in");
                        return this.eventFactory.createLogin(teamName, value);
                    default:
                        throw new CommException("Unbekannter Eventtyp!");
                }
            } catch (MessagePackException | IOException var10) {
                throw new CommException("Fehler beim Lesen des nächsten Events!", var10);
            }
        }
        return null;
    }

    public final void sendRegister(String name) {
        try {
            this.packer.packInt(0);
            this.packer.packString(name);
            this.packer.flush();
            this.socket.send(this.outputBuffer.toByteArray());
        } catch (MessagePackException | IOException var9) {
            throw new CommException("Ein 'Register' Command konnte nicht ins Wire-Format übersetzt werden!", var9);
        } catch (ZMQException var10) {
            if (var10.getErrorCode() == 65) {
                throw new CommException("Die andere Seite der Verbindung ist bereits geschlossen!", var10);
            }

            throw new CommException(String.format("Clientseitiger Commlibfehler %d! Bitte wenden Sie sich an Ihren Tutor!", var10.getErrorCode()), var10);
        } finally {
            this.outputBuffer.reset();
        }

    }

    public final void sendWatch() {
        try {
            this.packer.packInt(1);
            this.packer.flush();
            this.socket.send(this.outputBuffer.toByteArray());
        } catch (MessagePackException | IOException var6) {
            throw new CommException("Ein 'Watch' Command konnte nicht ins Wire-Format übersetzt werden!", var6);
        } catch (ZMQException var7) {
            if (var7.getErrorCode() == 65) {
                throw new CommException("Die andere Seite der Verbindung ist bereits geschlossen!", var7);
            }

            throw new CommException(String.format("Clientseitiger Commlibfehler %d! Bitte wenden Sie sich an Ihren Tutor!", var7.getErrorCode()), var7);
        } finally {
            this.outputBuffer.reset();
        }

    }

    public final void sendMove(int x, int y, String t, String name) {
        try {
            this.packer.packInt(2);
            this.packer.packInt(x);
            this.packer.packInt(y);
            this.packer.packString(t);
            this.packer.packString(name);
            this.packer.flush();
            this.socket.send(this.outputBuffer.toByteArray());
        } catch (MessagePackException | IOException var7) {
            throw new CommException("Ein 'Move' Command konnte nicht ins Wire-Format übersetzt werden!", var7);
        } catch (ZMQException var8) {
            if (var8.getErrorCode() == 65) {
                throw new CommException("Die andere Seite der Verbindung ist bereits geschlossen!", var8);
            }

            throw new CommException(String.format("Clientseitiger Commlibfehler %d! Bitte wenden Sie sich an Ihren Tutor!", var8.getErrorCode()), var8);
        } finally {
            this.outputBuffer.reset();
        }

    }

    public final void sendChat(String chat) {
        try {
            this.packer.packInt(8);
            this.packer.packString(chat);
            this.packer.flush();
            this.socket.send(this.outputBuffer.toByteArray());
        } catch (MessagePackException | IOException var7) {
            throw new CommException("Ein 'WarCry' Command konnte nicht ins Wire-Format übersetzt werden!", var7);
        } catch (ZMQException var8) {
            if (var8.getErrorCode() == 65) {
                throw new CommException("Die andere Seite der Verbindung ist bereits geschlossen!", var8);
            }

            throw new CommException(String.format("Clientseitiger Commlibfehler %d! Bitte wenden Sie sich an Ihren Tutor!", var8.getErrorCode()), var8);
        } finally {
            this.outputBuffer.reset();
        }

    }

    public final void sendCreateGame() {
        try {
            this.packer.packInt(3);
            this.packer.flush();
            this.socket.send(this.outputBuffer.toByteArray());
        } catch (MessagePackException | IOException var6) {
            throw new CommException("Ein 'DoneActing' Command konnte nicht ins Wire-Format übersetzt werden!", var6);
        } catch (ZMQException var7) {
            if (var7.getErrorCode() == 65) {
                throw new CommException("Die andere Seite der Verbindung ist bereits geschlossen!", var7);
            }

            throw new CommException(String.format("Clientseitiger Commlibfehler %d! Bitte wenden Sie sich an Ihren Tutor!", var7.getErrorCode()), var7);
        } finally {
            this.outputBuffer.reset();
        }

    }

    public final void sendGameFinished() {
        try {
            this.packer.packInt(4);
            this.packer.flush();
            this.socket.send(this.outputBuffer.toByteArray());
        } catch (MessagePackException | IOException var6) {
            throw new CommException("Ein 'DoneActing' Command konnte nicht ins Wire-Format übersetzt werden!", var6);
        } catch (ZMQException var7) {
            if (var7.getErrorCode() == 65) {
                throw new CommException("Die andere Seite der Verbindung ist bereits geschlossen!", var7);
            }

            throw new CommException(String.format("Clientseitiger Commlibfehler %d! Bitte wenden Sie sich an Ihren Tutor!", var7.getErrorCode()), var7);
        } finally {
            this.outputBuffer.reset();
        }
    }

    public final void sendGamePaused() {
        try {
            this.packer.packInt(5);
            this.packer.flush();
            this.socket.send(this.outputBuffer.toByteArray());
        } catch (MessagePackException | IOException var6) {
            throw new CommException("Ein 'DoneActing' Command konnte nicht ins Wire-Format übersetzt werden!", var6);
        } catch (ZMQException var7) {
            if (var7.getErrorCode() == 65) {
                throw new CommException("Die andere Seite der Verbindung ist bereits geschlossen!", var7);
            }

            throw new CommException(String.format("Clientseitiger Commlibfehler %d! Bitte wenden Sie sich an Ihren Tutor!", var7.getErrorCode()), var7);
        } finally {
            this.outputBuffer.reset();
        }
    }

    public final void sendLogin(String name, int id) {
        try {
            this.packer.packInt(10);
            this.packer.packString(name);
            this.packer.packInt(id);
            this.packer.flush();
            this.socket.send(this.outputBuffer.toByteArray());
        } catch (MessagePackException | IOException var9) {
            throw new CommException("Ein 'Login' Command konnte nicht ins Wire-Format übersetzt werden!", var9);
        } catch (ZMQException var10) {
            if (var10.getErrorCode() == 65) {
                throw new CommException("Die andere Seite der Verbindung ist bereits geschlossen!", var10);
            }

            throw new CommException(String.format("Clientseitiger Commlibfehler %d! Bitte wenden Sie sich an Ihren Tutor!", var10.getErrorCode()), var10);
        } finally {
            this.outputBuffer.reset();
        }

    }

    static final class Events {
        static final int REGISTERED = 0;
        static final int WATCHED = 1;
        static final int MOVED = 2;
        static final int CREATED_GAME = 3;
        static final int GAME_FINISHED = 4;
        static final int GAME_PAUSED = 5;
        static final int DONED = 6;
        static final int LOST_PWED = 7;
        static final int CHATED = 8;
        static final int NEWPLAYERED = 9;
        static final int LOGIN = 10;

        private Events() {
        }
    }
}
