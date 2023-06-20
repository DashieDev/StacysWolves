package stacywolves.common.networking;

import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import stacywolves.common.lib.Constants;


public final class PacketHandler {

    public static final SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder.named(Constants.CHANNEL_NAME)
            .clientAcceptedVersions(Constants.PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(Constants.PROTOCOL_VERSION::equals)
            .networkProtocolVersion(Constants.PROTOCOL_VERSION::toString)
            .simpleChannel();

    private static int disc = 0;

    public static void init() {
    }

    public static <MSG> void send(PacketDistributor.PacketTarget target, MSG message) {
        HANDLER.send(target, message);
    }

    public static <D> void registerPacket(IPacket<D> packet, Class<D> dataClass) {
        HANDLER.registerMessage(PacketHandler.disc++, dataClass, packet::encode, packet::decode, packet::handle);
    }
}
